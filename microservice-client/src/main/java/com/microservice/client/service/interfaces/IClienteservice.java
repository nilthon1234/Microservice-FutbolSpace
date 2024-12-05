package com.microservice.client.service.interfaces;

import com.microservice.client.persistence.models.Cliente;
import com.microservice.client.presentation.dto.ClienteDto;
import com.microservice.client.service.http.response.ResponseCliente;

import java.util.List;
import java.util.Map;

public interface IClienteservice {
    void addClient(Cliente cliente);
    ResponseCliente listAllAcces();
    Map<String, String> login (int dni, String password);
    
    //admin
    List<ClienteDto> listAllCliente();
}
