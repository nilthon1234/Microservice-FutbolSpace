package com.microservice.client.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.client.persistence.models.Cliente;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDto {
	
	private String id;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    @Min(value = 10000000, message = "El dni debe tener almenos 8 digitos")
    @Indexed(unique = true)
    private int dni;
    @NotNull
    @Pattern(regexp = "\\d{10,}", message = "el telefono debe tener almenos 10 digitos")
    @Indexed(unique = true)
    private String phone;
    @NotNull
    @Email
    @Indexed(unique = true)
    private String email;
    @NotNull
    @Size(min = 6, message = "La contraseña debe tener almenos 6 caracteres")
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
