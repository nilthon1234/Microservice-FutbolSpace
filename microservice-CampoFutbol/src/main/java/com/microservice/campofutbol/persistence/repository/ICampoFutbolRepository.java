package com.microservice.campofutbol.persistence.repository;

import com.microservice.campofutbol.persistence.models.CampoFutbol;
import com.microservice.campofutbol.presentation.dto.CampoFutbolDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICampoFutbolRepository  extends JpaRepository<CampoFutbol, Long> {

      List<CampoFutbol> findByEstadoEntity (CampoFutbol.Estado estado);
      List<CampoFutbol> findByDniEntity(long dniEntity);

}
