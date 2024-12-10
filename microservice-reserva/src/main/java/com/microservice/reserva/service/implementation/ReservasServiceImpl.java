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

        if (!verificarDisponibilidad(campoFutbol,inicio,fin)) {
            throw new IllegalArgumentException("El rango de hora que ingresas ya estan reservadas por otro Cliente");

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
    public List<ResponseReserva> searchDniClient(int dniCliente) {
        List<Reservas> lista = reservaRepository.findByDniCliente(dniCliente);
        if (lista.isEmpty()) {
            throw  new IllegalArgumentException("no se encontro dniCliente: " + dniCliente);
        }
        List<ResponseReserva> responseReservas = new ArrayList<>();
        for (Reservas reservas : lista) {

            List<CampoFutbolDto> campoFutbolDto = campoFutbolFeignClient.listIdCampoFutbol(reservas.getIdCampoFutbol());
            if (campoFutbolDto.isEmpty()) {
                throw  new IllegalArgumentException("no se encontro idCampoFutbol: " + reservas.getIdCampoFutbol());
            }

            ResponseReserva response = ResponseReserva.builder()
                    .campoFutbol(reservas.getIdCampoFutbol())
                    .dniCliente(reservas.getDniCliente())
                    .fecha(reservas.getFecha())
                    .horaInicio(reservas.getHoraInicio().toLocalTime())
                    .horaFin(reservas.getHoraFin().toLocalTime())
                    .listaCampoFutbol(campoFutbolDto)
                    .build();
            responseReservas.add(response);
        }
    return responseReservas;

    }

	@Override
	public List<ReservaDto> allListReservas() {
		// TODO Auto-generated method stub
		return reservaRepository.findAll().stream()
				.map(ReservaDto::new)
				.collect(Collectors.toList());
	}

    @Override
    public List<ReservaDto> findByDniClient(int dniClient) {
        return  reservaRepository.findByDniCliente(dniClient).stream()
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
