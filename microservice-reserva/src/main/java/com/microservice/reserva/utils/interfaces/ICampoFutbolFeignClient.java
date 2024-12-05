package com.microservice.reserva.utils.interfaces;

import com.microservice.reserva.presentation.dto.CampoFutbolDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-CampoFutbol", url = "http://localhost:8085/campo")
public interface ICampoFutbolFeignClient {

     @GetMapping("/client/{id}")
    List<CampoFutbolDto> listIdCampoFutbol(@PathVariable long id);
}
