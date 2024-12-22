package com.file.image.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagenFileDto {

    private Long id;
    @NotNull(message = "el idCampoFutbol no tiene por que estar vacio")
    private int idCampoFutbol;
    @NotBlank(message = "el campo Imagen01 no tiene por que estar vacio")
    private String imagen01;
    @NotBlank(message = "el campo Imagen02 no tiene por que estar vacio")
    private String imagen02;
    @NotBlank(message = "el campo Imagen03 no tiene por que estar vacio")
    private String imagen03;
    private String imagen01Url;
    private String imagen02Url;
    private String imagen03Url;

}
