package com.microservice.reserva.service.interfaces;

import com.microservice.reserva.persistence.models.Reservas;
import com.microservice.reserva.presentation.dto.ReservaDto;
import com.microservice.reserva.service.http.response.ResponseReserva;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface IReservaService {

    Reservas registrarReserva(int campoFutbol, int cliente, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);
    List<ResponseReserva> searchDniClient(int dniCliente);
    List<ReservaDto> allListReservas();
    List<ReservaDto> findByDniClient (int dniClient);

}
