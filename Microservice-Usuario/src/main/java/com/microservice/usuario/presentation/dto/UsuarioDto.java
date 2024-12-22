package com.microservice.usuario.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.usuario.persistence.models.Usuario;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String dni;
    private String phone;
    private String estado;
    
    public UsuarioDto(Usuario usuario) { 
    	this.id = usuario.getId(); 
    	this.name = usuario.getNameEntity();
    	this.lastname = usuario.getLastnameEntity(); 
    	this.email = usuario.getEmailEntity(); 
    	this.password = usuario.getPasswordEntity(); 
    	this.dni = usuario.getDniEntity(); 
    	this.phone = usuario.getPhoneEntity(); 
    	this.estado = usuario.getEstadoEntity().name(); }
    
}
