package com.microservice.campofutbol.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImagenFileDto {
    private String imagen01Url;
    private String imagen02Url;
    private String imagen03Url;
}
