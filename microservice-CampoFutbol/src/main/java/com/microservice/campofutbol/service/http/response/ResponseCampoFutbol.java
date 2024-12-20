package com.microservice.campofutbol.service.http.response;

import com.microservice.campofutbol.presentation.dto.ImagenFileDto;
import com.microservice.campofutbol.presentation.dto.ReservaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseCampoFutbol {
    private Long id;
    private  String address;
    private String description;
    private String city;
    private String province;
    private String district;
    private List<ReservaDto> reservas;
    private List<ImagenFileDto> fileImagen;
}
