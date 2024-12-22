package com.microservice.usuario.service.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.usuario.presentation.dto.CampoFutbolDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseUsuario {
    private String dni;
    private String name;
    private String phone;
    private List<CampoFutbolDto> CampoFutbol;
}
