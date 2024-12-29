package com.microservice.usuario.persistence.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_user")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message = "este nombre no puede estar vacio")
    @Column(name = "name")
    private String nameEntity;
    @NotNull
    @NotBlank(message = "este lastname no puede estar vacio")
    @Column(name = "lastname")
    private String lastnameEntity;

    @NotBlank(message = "este email no puede estar vacia")
    @Email
    @Column(name = "email", unique = true)
    private String emailEntity;

    @NotBlank(message = "este password no puede estar vacio")
    @Column(name = "password")
    private String passwordEntity;

    @NotNull(message = "el dni no puede estar nulo")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    @Column(name = "dni_use", unique = true)
    private String dniEntity;

    @NotNull(message = "el phone no puede estar nulo")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener exactamente 9 dígitos")
    @Column(name = "phone", unique = true)
    private String phoneEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estadoEntity = Estado.Acces;

    public enum Estado{
        Acces,
        NotAcces
    }
}
