package com.file.image.utils.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.file.image.persistence.models.FileImagen;
import com.file.image.presentation.dto.ImagenFileDto;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileImagenMapper {
	

    @Value("${project.poster}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;
	
	public ImagenFileDto converDto(FileImagen file) {
		
		String url1 = baseUrl + "/file/" + file.getImagen01();
		String url2 = baseUrl + "/file/" + file.getImagen02();
		String url3 = baseUrl + "/file/" + file.getImagen03();
		
		return ImagenFileDto.builder()
				.idCampoFutbol(file.getIdCampoFutbol())
				.imagen01Url(url1)
				.imagen02Url(url2)
				.imagen03Url(url3)
				.build();
	}

}
