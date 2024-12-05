package com.microservice.campofutbol.service.implementation;

import com.microservice.campofutbol.persistence.models.CampoFutbol;
import com.microservice.campofutbol.persistence.repository.ICampoFutbolRepository;
import com.microservice.campofutbol.presentation.dto.CampoFutbolDto;
import com.microservice.campofutbol.service.interfaces.ICampoFutbolService;
import com.microservice.campofutbol.utils.mapper.CampoFutbolMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampoFutbolServiceImpl implements ICampoFutbolService {
    @Autowired
    private ICampoFutbolRepository iCampoFutbolRepository;
    @Autowired
    private CampoFutbolMapper campoFutbolMapper;
    @Override
    public List<CampoFutbolDto> listHabilitado() {
        List<CampoFutbol> lista = iCampoFutbolRepository.findByEstadoEntity(CampoFutbol.Estado.HABILITADO);
        return campoFutbolMapper.campoFutbolDtoList(lista);

    }

    @Override
    public List<CampoFutbolDto> searchByDni( long dniEntity) {
        List<CampoFutbol> search = iCampoFutbolRepository.findByDniEntity(dniEntity);
        return search.stream()
                .map(campoFutbolMapper::allCamposDto)
                .collect(Collectors.toList());
    }

    @Override
    public CampoFutbol addCampoFutbol(CampoFutbolDto mydto) {
        CampoFutbol campoFutbol = new CampoFutbol();
        CampoFutbol obtained = campoFutbolMapper.myCampoFutbolDto(mydto, campoFutbol);
        return iCampoFutbolRepository.save(obtained);
    }

    @Override
    public CampoFutbol actusCampoUsua(Long idCampo, CampoFutbolDto mydto) {
        CampoFutbol campoFutbol = iCampoFutbolRepository.findById(idCampo)
                .orElseThrow(() -> new RuntimeException("Campo de futbol no encontrada"));
        CampoFutbol request = campoFutbolMapper.myCampoFutbolDto(mydto, campoFutbol);
       return iCampoFutbolRepository.save(request);

    }
    @Override
    public CampoFutbol actusCampoadmin(Long idCampo, CampoFutbolDto mydto) {
        return iCampoFutbolRepository.findById(idCampo)
                .map(actus -> {
                    actus.setEstadoEntity(CampoFutbol.Estado.valueOf(mydto.getEstado()));
                    return iCampoFutbolRepository.save(actus);
                })
                .orElseThrow(() -> new IllegalArgumentException("Id invalido:" + idCampo));
    }

    @Override
    public List<CampoFutbolDto> allListCampo() {
        return iCampoFutbolRepository.findAll()
                .stream()
                .map(campoFutbolMapper::allCamposDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CampoFutbolDto> searchById(Long id) {
        Optional<CampoFutbol> search = iCampoFutbolRepository.findById(id);
        if (search.isPresent()) {
            return search.stream()
                    .map(campoFutbolMapper::allCamposDto)
                    .collect(Collectors.toList());
        }
        throw  new EntityNotFoundException(" fallo el id: " + id + " no encontrado" );
    }


}
