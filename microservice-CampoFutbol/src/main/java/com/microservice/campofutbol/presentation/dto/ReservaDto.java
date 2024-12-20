package com.microservice.campofutbol.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservaDto {


    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int campoFutbol;
}
