package com.file.image.service.implementation;

import com.file.image.service.interfaces.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename; // Generar nombre único
        String filePath = path + File.separator + uniqueFilename;
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // Crear todos los directorios necesarios
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return uniqueFilename;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws IOException {
        String filePath = path + File.separator + filename;
        return new FileInputStream(filePath);
    }
}

