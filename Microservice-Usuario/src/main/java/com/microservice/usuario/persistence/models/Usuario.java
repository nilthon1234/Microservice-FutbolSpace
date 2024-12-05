package com.microservice.usuario.persistence.models;

import jakarta.persistence.*;
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
    @Column(name = "name")
    private String nameEntity;
    @Column(name = "lastname")
    private String lastnameEntity;
    @Column(name = "email")
    private String emailEntity;
    @Column(name = "password")
    private String passwordEntity;
    @Column(name = "dni_use")
    private long dniEntity;
    @Column(name = "phone")
    private int phoneEntity;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estadoEntity = Estado.Acces;

    public enum Estado{
        Acces,
        NotAcces
    }
}
