package com.file.image.persistence.repository;

import com.file.image.persistence.models.FileImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileImagenRepository extends JpaRepository<FileImagen, Long> {
}
