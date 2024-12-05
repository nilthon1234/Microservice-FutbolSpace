package com.microservice.reserva.service.http.response;

import com.microservice.reserva.presentation.dto.CampoFutbolDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseReserva {

    private int dniCliente;
    private int campoFutbol;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<CampoFutbolDto> listaCampoFutbol;
}
