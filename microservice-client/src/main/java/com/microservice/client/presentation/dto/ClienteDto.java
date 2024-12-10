package com.microservice.client.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.client.persistence.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDto {
	
	private String id;
    private String name;
    private String lastname;
    private int dni;
    private String phone;
    private String email;
    private String password;
    public ClienteDto(Cliente cliente) {
    	this.id = cliente.getId();
    	this.name = cliente.getName();
    	this.lastname = cliente.getLastname();
    	this.email = cliente.getEmail();
    	this.dni = cliente.getDni();
    	this.phone = cliente.getPhone();
    }

}
