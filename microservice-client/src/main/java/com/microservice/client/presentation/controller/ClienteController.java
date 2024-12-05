package com.microservice.client.presentation.controller;

import com.microservice.client.persistence.models.Cliente;
import com.microservice.client.presentation.dto.ClienteDto;
import com.microservice.client.service.http.response.ResponseCliente;
import com.microservice.client.service.implementation.ClienteServiceImpl;
import com.microservice.client.service.interfaces.IClienteservice;
import com.microservice.client.utils.ResponseClientLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class ClienteController {
	
    private final IClienteservice iClienteService;

    public ClienteController(ClienteServiceImpl iClienteService){
        this. iClienteService = iClienteService;
    }
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(iClienteService.listAllAcces());
    }
    @PostMapping("/client/register")
    public void add(@RequestBody Cliente cliente){
        iClienteService.addClient(cliente);
    }

    @PostMapping("/client/login")
    public ResponseEntity<?> logeo(@RequestBody Cliente cliente){
        Map<String, String> response = iClienteService.login(cliente.getDni(), cliente.getPassword());
        return  ResponseClientLogin.responseloginAcc(response);
    }
    
    @GetMapping("/admin/list-client")
    public List<ClienteDto> listAllClient(){
    	return iClienteService.listAllCliente();
    }

}
