package com.microservice.reserva.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.reserva.persistence.models.Reservas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    
    public ReservaDto(Reservas reservas) {
    	this.id = reservas.getId();
    	this.fecha = reservas.getFecha();
    	this.horaInicio = reservas.getHoraInicio().toLocalTime();
    	this.horaFin = reservas.getHoraFin().toLocalTime();
    	this.dniCliente = reservas.getDniCliente();
    	this.campoFutbol = reservas.getIdCampoFutbol();
    	
    }


}
