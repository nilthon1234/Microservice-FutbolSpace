package com.microservice.client.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaDto {

    private Long id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int dniCliente;
    private int campoFutbol;
    private List<CampoFutbolDto> campoFutbols;
}
