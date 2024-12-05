package com.file.image.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private int idCampoFutbol;
    private String imagen01;
    private String imagen02;
    private String imagen03;
    private String imagen01Url;
    private String imagen02Url;
    private String imagen03Url;

}
