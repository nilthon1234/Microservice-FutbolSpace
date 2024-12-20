package com.microservice.client.utils.interfaces;

import com.microservice.client.presentation.dto.ReservaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-reserva", url = "localhost:8082/reserva")
public interface ReservasClient {

    @GetMapping("/client/search/{dniCliente}")
    List<ReservaDto>listReservas(@PathVariable int dniCliente);
}
