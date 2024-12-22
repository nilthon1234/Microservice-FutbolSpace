package com.microservice.client.persistence.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Pattern(regexp = "\\d{9,}", message = "el telefono debe tener almenos 9 digitos")
    @Indexed(unique = true)
    private String phone;
    @NotNull
    @Email
    @Indexed(unique = true)
    private String email;
    @NotNull
    @Size(min = 6, message = "La contraseña debe tener almenos 6 caracteres")
    private String password;

}
