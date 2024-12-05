package com.microservice.reserva.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_reserva")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dni_client", nullable = false)
    private int dniCliente;
    @Column(name = "id_campo_futbol", nullable = false)
    private int idCampoFutbol;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(name = "hora_Inicio", nullable = false)
    private LocalDateTime horaInicio;
    @Column(name = "hora_Fin", nullable = false)
    private LocalDateTime horaFin;
}
