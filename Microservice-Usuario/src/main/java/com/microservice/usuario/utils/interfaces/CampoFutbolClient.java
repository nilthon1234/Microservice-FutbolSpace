package com.microservice.usuario.utils.interfaces;

import com.microservice.usuario.presentation.dto.CampoFutbolDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-CampoFutbol", url = "localhost:8085/campo")
public interface CampoFutbolClient {
    @GetMapping("/usu/dni/{dniEntity}")
    List<CampoFutbolDto> shearchDni(@PathVariable String dniEntity);
}
