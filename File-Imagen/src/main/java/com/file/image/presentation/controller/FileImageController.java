package com.file.image.presentation.controller;

import com.file.image.presentation.dto.ImagenFileDto;
import com.file.image.service.interfaces.IFileImagenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileImageController {

    private final IFileImagenService fileImagenService;

    public FileImageController(IFileImagenService fileImagenService) {
        this.fileImagenService = fileImagenService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> uploadFile(@RequestParam("idCampoFutbol") int idCampoFutbol,
                                        @RequestParam("imagenes") List<MultipartFile> imagenes) {
        try {
            ImagenFileDto imagenFileDto = new ImagenFileDto();
            imagenFileDto.setIdCampoFutbol(idCampoFutbol);
            ImagenFileDto response = fileImagenService.addImage(imagenFileDto, imagenes);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR el motivo es que ya hay imagenes con ese nombre en la carpeta: " + e.getMessage());

        }

    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<List<ImagenFileDto>> listAllIdCampo(@PathVariable int id){
    	List<ImagenFileDto> response = fileImagenService.getFileImagenDto(id);
    	
    	return ResponseEntity.ok(response);
    }


}
