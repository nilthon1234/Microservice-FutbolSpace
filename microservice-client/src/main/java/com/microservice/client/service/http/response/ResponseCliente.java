package com.microservice.client.service.http.response;

import com.microservice.client.presentation.dto.CampoFutbolDto;
import com.microservice.client.presentation.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseCliente {
    private List<CampoFutbolDto> campoFutbolDtoList;
    private List<UsuarioDto> usuarioDtoList;
}
