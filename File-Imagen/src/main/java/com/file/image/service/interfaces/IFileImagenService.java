package com.file.image.service.interfaces;

import com.file.image.presentation.dto.ImagenFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileImagenService {

    ImagenFileDto addImage(ImagenFileDto imagenFileDto, List<MultipartFile> file) throws IOException;
    List<ImagenFileDto> getFileImagenDto ( int idCampoFutbol );
}
	