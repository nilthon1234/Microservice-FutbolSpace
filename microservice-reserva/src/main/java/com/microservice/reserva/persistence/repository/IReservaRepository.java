package com.microservice.reserva.persistence.repository;

import com.microservice.reserva.persistence.models.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IReservaRepository extends JpaRepository<Reservas, Long> {
   // consulta personalizada con JPQL
   @Query("SELECT r FROM Reservas r WHERE r.idCampoFutbol = :campoFutbol " +
           "AND (r.horaInicio < :horaFin AND r.horaFin > :horaInicio)")
   List<Reservas> findConflictingReservations(
           @Param("campoFutbol") int campoFutbol,
           @Param("horaInicio") LocalDateTime horaInicio,
           @Param("horaFin") LocalDateTime horaFin);

   List<Reservas> findByDniCliente(int dniCliente );

}
