package com.file.image.persistence.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file_imagen")
@Data
public class FileImagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "el idCampoFutbol no tiene por que estar vacio")
    private int idCampoFutbol;
    @NotBlank(message = "el campo Imagen01 no tiene por que estar vacio")
    private String imagen01;
    @NotBlank(message = "el campo Imagen02 no tiene por que estar vacio")
    private String imagen02;
    @NotBlank(message = "el campo Imagen03 no tiene por que estar vacio")
    private String imagen03;
}
