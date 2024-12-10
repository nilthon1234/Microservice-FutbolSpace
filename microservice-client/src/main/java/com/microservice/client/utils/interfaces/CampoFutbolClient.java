package com.microservice.client.utils.interfaces;

import com.microservice.client.presentation.dto.CampoFutbolDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-CampoFutbol", url = "localhost:8085/campo")
public interface CampoFutbolClient {
    @GetMapping("/client/list-habilitados")
    List<CampoFutbolDto> listAllAccesCampo();

    @GetMapping("/client/{id}")
    List<CampoFutbolDto> listaIdCampoFutbol(@PathVariable  long id);


}
