package com.microservice.campofutbol.utils.interfaces;

import com.microservice.campofutbol.presentation.dto.ReservaDto;
import com.microservice.campofutbol.service.http.response.ResponseCampoFutbol;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-reserva", url = "localhost:8082/reserva")
public interface IReservasClient {
    @GetMapping("/client/campo/{idCampo}")
    List<ReservaDto> listaReservasIdCampo(@PathVariable long idCampo);


}
