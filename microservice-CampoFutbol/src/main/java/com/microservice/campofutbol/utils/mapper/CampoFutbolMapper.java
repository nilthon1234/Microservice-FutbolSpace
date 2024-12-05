package com.microservice.campofutbol.utils.mapper;

import com.microservice.campofutbol.persistence.models.CampoFutbol;
import com.microservice.campofutbol.presentation.dto.CampoFutbolDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampoFutbolMapper {

    public CampoFutbolDto myLisCampoFutbolDto (CampoFutbol campoFutbol){
        CampoFutbolDto mydto = new CampoFutbolDto();
        mydto.setAddress(campoFutbol.getAddressEntity());
        mydto.setDniUse(campoFutbol.getDniEntity());
        mydto.setDescription(campoFutbol.getDescriptionEntity());
        mydto.setCity(campoFutbol.getCityEntity());
        mydto.setProvince(campoFutbol.getProvinceEntity());
        mydto.setDistrict(campoFutbol.getDistrictEntity());
        return  mydto;
    }
    public List<CampoFutbolDto> campoFutbolDtoList(List<CampoFutbol> campoFutboList){
        return campoFutboList.stream()
                //map para transforar
                .map(this::myLisCampoFutbolDto)
                //collection para alamacenar
                .collect(Collectors.toList());
    }

    public CampoFutbol myCampoFutbolDto (CampoFutbolDto dto, CampoFutbol entity){
        entity.setDniEntity(dto.getDniUse());
        entity.setAddressEntity(dto.getAddress());
        entity.setDescriptionEntity(dto.getDescription());
        entity.setCityEntity(dto.getCity());
        entity.setProvinceEntity(dto.getProvince());
        entity.setDistrictEntity(dto.getDistrict());
        return entity;
    }
    public CampoFutbolDto allCamposDto(CampoFutbol entity){
        CampoFutbolDto mydto = new CampoFutbolDto();
        mydto.setId(entity.getId());
        mydto.setAddress(entity.getAddressEntity());
        mydto.setDniUse(entity.getDniEntity());
        mydto.setDescription(entity.getDescriptionEntity());
        mydto.setCity(entity.getCityEntity());
        mydto.setProvince(entity.getProvinceEntity());
        mydto.setDistrict(entity.getDistrictEntity());
        mydto.setEstado(entity.getEstadoEntity().name());
        return mydto;
    }
}
