package com.microservice.usuario.utils.interfaces;

import com.microservice.usuario.presentation.dto.ImagenFileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "File-Imagen", url = "localhost:8087/file")
public interface FileImagenClient {
    @GetMapping("/buscar/{id}")
    public List<ImagenFileDto> getListIdCampoFutbol(@PathVariable long id);
	
	

}
