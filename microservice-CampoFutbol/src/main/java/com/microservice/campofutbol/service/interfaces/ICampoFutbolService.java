package com.microservice.campofutbol.service.interfaces;

import com.microservice.campofutbol.persistence.models.CampoFutbol;
import com.microservice.campofutbol.presentation.dto.CampoFutbolDto;

import java.util.List;

public interface ICampoFutbolService {

    List<CampoFutbolDto> listHabilitado ();
    List<CampoFutbolDto> searchByDni(long dniEntity);
    CampoFutbol addCampoFutbol(CampoFutbolDto mydto);
    CampoFutbol actusCampoUsua(Long idCampo, CampoFutbolDto mydto);
    CampoFutbol actusCampoadmin(Long idCampo, CampoFutbolDto mydto);
    List<CampoFutbolDto> allListCampo();
    List<CampoFutbolDto>searchById(Long id);
}
