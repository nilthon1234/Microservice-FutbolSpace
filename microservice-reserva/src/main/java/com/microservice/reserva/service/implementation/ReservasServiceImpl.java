package com.microservice.reserva.service.implementation;

import com.microservice.reserva.persistence.models.Reservas;
import com.microservice.reserva.persistence.repository.IReservaRepository;
import com.microservice.reserva.presentation.dto.CampoFutbolDto;
import com.microservice.reserva.presentation.dto.ReservaDto;
import com.microservice.reserva.service.http.response.ResponseReserva;
import com.microservice.reserva.service.interfaces.IReservaService;
import com.microservice.reserva.utils.interfaces.ICampoFutbolFeignClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservasServiceImpl implements IReservaService {
    @Autowired
    private IReservaRepository reservaRepository;
    @Autowired
    private ICampoFutbolFeignClient campoFutbolFeignClient;

    private  boolean verificarDisponibilidad(int campoFutbol, LocalDateTime horaInicio, LocalDateTime fin) {

        // Llama al repositorio usando los LocalDateTime generados
        List<Reservas> conflic = reservaRepository.findConflictingReservations(campoFutbol,horaInicio, fin);
        return conflic.isEmpty();

    }

    @Transactional
    @Override
    public Reservas registrarReserva( int campoFutbol, int cliente, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        LocalDateTime inicio = LocalDateTime.of( fecha, horaInicio);
        LocalDateTime fin = LocalDateTime.of(fecha, horaFin);

        if (horaFin.isBefore(horaInicio)){
            throw new IllegalArgumentException("La HoraFin: " + horaFin + " no puede ser menor que la HoraInicio: " + horaInicio);
        }
        if (horaInicio.equals(horaFin)){
            throw new IllegalArgumentException("La Hora Inicio: " + horaInicio + " no puede ser la misma hora Fin: " + horaFin);
        }
        if (!verificarDisponibilidad(campoFutbol,inicio,fin)) {
            throw new IllegalArgumentException("El rango de fecha y hora que ingresas ya estan reservadas");

        }
        Reservas reserva = new Reservas();
        reserva.setDniCliente(cliente);
        reserva.setIdCampoFutbol(campoFutbol);
        reserva.setFecha(fecha);
        reserva.setHoraInicio(inicio);
        reserva.setHoraFin(fin);
        return reservaRepository.save(reserva);
    }

    @Override
    public List<ReservaDto> searchDniClient(int dniCliente) {
        List<Reservas> reservas = reservaRepository.findByDniCliente(dniCliente);
        return reservas.stream()
                .map(reservas1 -> {
                    return ReservaDto.builder()
                            .id(reservas1.getId())
                            .fecha(reservas1.getFecha())
                            .horaInicio(reservas1.getHoraInicio().toLocalTime())
                            .horaFin(reservas1.getHoraFin().toLocalTime())
                            .dniCliente(reservas1.getDniCliente())
                            .campoFutbol(reservas1.getIdCampoFutbol())
                            .build();

                })
                .collect(Collectors.toList());
    }
	@Override
	public List<ReservaDto> allListReservas() {
		// TODO Auto-generated method stub
		return reservaRepository.findAll().stream()
				.map(ReservaDto::new)
				.collect(Collectors.toList());
	}

    //metodo sera consumida por microservice -client
    @Override
    public List<ReservaDto> findByDniClient(int dniClient) {
        return reservaRepository.findByDniCliente(dniClient).stream()
                .sorted(Comparator.comparing(Reservas::getFecha)
                        .thenComparing(Reservas::getHoraInicio)) // Ordenamos por fecha y hora
                .skip(Math.max(0, reservaRepository.findByDniCliente(dniClient).size() - 3)) // Saltamos hasta las últimas 3 reservas
                .map(ReservaDto::new) //Mapeamos
                .collect(Collectors.toList());
    }


    //metodo por los 3 dias de hoy hasta mañana pasado
    @Override
    public List<ReservaDto> finfByIdCampoFutbol(int idCampoFutbol) {
        LocalDate today = LocalDate.now();
        LocalDate dayAfterTomorrow = today.plusDays(2);
        return reservaRepository.findReservasByCampoAndDateRange(idCampoFutbol,today,dayAfterTomorrow).stream()
                .map(r -> new ReservaDto(
                        r.getId(),
                        r.getFecha(),
                        r.getHoraInicio().toLocalTime(),
                        r.getHoraFin().toLocalTime(),
                        r.getDniCliente(),
                        r.getIdCampoFutbol()
                ))
                .collect(Collectors.toList());
    }




}
