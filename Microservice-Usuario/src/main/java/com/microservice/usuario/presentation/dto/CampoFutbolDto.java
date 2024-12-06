package com.microservice.usuario.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CampoFutbolDto {

    private Long id;
    private long dniUse;
    private  String address;
    private String description;
    private String city;
    private String province;
    private String district;
    private String estado;
    private List<ImagenFileDto> fileImagen;
}