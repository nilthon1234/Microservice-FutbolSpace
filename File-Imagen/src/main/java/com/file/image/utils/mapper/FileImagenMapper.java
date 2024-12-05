package com.file.image.utils.mapper;

import com.file.image.persistence.models.FileImagen;
import com.file.image.presentation.dto.ImagenFileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileImagenMapper {

    @Value("${project.poster}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;

    public ImagenFileDto convertDto(FileImagen file){
        String imagen01Url = baseUrl + "/file/" + file.getImagen01();
        String imagen02Url = baseUrl + "/file/" + file.getImagen01();
        String imagen03Url = baseUrl + "/file/" + file.getImagen01();

        return ImagenFileDto.builder()
                .idCampoFutbol(file.getIdCampoFutbol())
                .imagen01Url(imagen01Url)
                .imagen02(imagen02Url)
                .imagen03Url(imagen03Url)
                .build();
    }
}
