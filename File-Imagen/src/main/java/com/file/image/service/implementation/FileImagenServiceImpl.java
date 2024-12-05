package com.file.image.service.implementation;

import com.file.image.persistence.models.FileImagen;
import com.file.image.persistence.repository.IFileImagenRepository;
import com.file.image.presentation.dto.ImagenFileDto;
import com.file.image.service.interfaces.FileService;
import com.file.image.service.interfaces.IFileImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileImagenServiceImpl implements IFileImagenService {

    private final IFileImagenRepository repository;
    private final FileService fileService;

    @Autowired
    public FileImagenServiceImpl(IFileImagenRepository repository, FileService fileService) {
        this.repository = repository;
        this.fileService = fileService;
    }
    @Value("${project.poster}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;

    @Override
    public ImagenFileDto addImage(ImagenFileDto imagenFileDto, List<MultipartFile> file) throws IOException {
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
        String imagenUrl1 = baseUrl + "/file/" + fileImagen.getImagen01();
        String imagenUrl2 = baseUrl + "/file/" + fileImagen.getImagen02();
        String imagenUrl3 = baseUrl + "/file/" + fileImagen.getImagen03();
        fileImagen = repository.save(fileImagen);
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
}
