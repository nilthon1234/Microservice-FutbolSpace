package com.file.image.service.implementation;

import com.file.image.persistence.models.FileImagen;
import com.file.image.persistence.repository.IFileImagenRepository;
import com.file.image.presentation.dto.ImagenFileDto;
import com.file.image.service.interfaces.FileService;
import com.file.image.service.interfaces.IFileImagenService;
import com.file.image.utils.mapper.FileImagenMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class FileImagenServiceImpl implements IFileImagenService {

    private final IFileImagenRepository repository;
    private final FileService fileService;
    private final FileImagenMapper fileImagenMapper;

    @Autowired
    public FileImagenServiceImpl(FileImagenMapper fileImagenMapper,IFileImagenRepository repository, FileService fileService) {
        this.fileImagenMapper = fileImagenMapper;
    	this.repository = repository;
        this.fileService = fileService;
    }
    @Value("${project.poster}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;

    @Override
    public ImagenFileDto addImage(ImagenFileDto imagenFileDto, List<MultipartFile> file) throws IOException {
        for (int i = 0; i < file.size(); i++){
            MultipartFile imagen = file.get(i);
            if (imagen.isEmpty()) {
                throw new IllegalArgumentException("La Imagen " + (i + 1) + " esta vacia");
            }
        }

        FileImagen fileImagen = new FileImagen();
        fileImagen.setIdCampoFutbol(imagenFileDto.getIdCampoFutbol());

            for (int i = 0; i < file.size(); i++) {
                String fileName = fileService.uploadFile(path, file.get(i));
                switch (i) {
                    case 0:
                        fileImagen.setImagen01(fileName);
                        break;
                    case 1:
                        fileImagen.setImagen02(fileName);
                        break;
                    case 2:
                        fileImagen.setImagen03(fileName);
                        break;
                    default:
                        throw new IllegalArgumentException("Solo se permite 3 imagenes");
                }
            }

        fileImagen = repository.save(fileImagen);
        //concatenamos
        String imagenUrl1 = baseUrl + "/file/" + fileImagen.getImagen01();
        String imagenUrl2 = baseUrl + "/file/" + fileImagen.getImagen02();
        String imagenUrl3 = baseUrl + "/file/" + fileImagen.getImagen03();
        ImagenFileDto fileDto = ImagenFileDto.builder()
                .id(fileImagen.getId())
                .idCampoFutbol(fileImagen.getIdCampoFutbol())
                .imagen01(fileImagen.getImagen01())
                .imagen02(fileImagen.getImagen02())
                .imagen03(fileImagen.getImagen03())
                .imagen01Url(imagenUrl1)
                .imagen02Url(imagenUrl2)
                .imagen03Url(imagenUrl3)
                .build();
        return fileDto;
    }

	@Override
	public List<ImagenFileDto> getFileImagenDto(int idCampoFutbol) {
		
		List<FileImagen> list = repository.findByIdCampoFutbol(idCampoFutbol);
		// TODO Auto-generated method stub
		return list.stream()
				.map(this.fileImagenMapper::converDto)
				.collect(Collectors.toList());
	}
}
