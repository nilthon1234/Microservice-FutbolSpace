package com.microservice.client.utils.interfaces;

import com.microservice.client.presentation.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "Microservice-Usuario", url = "localhost:8080/usuario")
public interface UsuarioClient {

    @GetMapping("/client/list-access")
    List<UsuarioDto> listAllAccessUsuario();
}
