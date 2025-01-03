package com.microservice.usuario.persistence.repository;

import com.microservice.usuario.persistence.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEstadoEntity(Usuario.Estado estado);
    Optional<Usuario> findByDniEntity(String dniEntity);
    Optional<Usuario> findByDniEntityAndPasswordEntity(String dniEntity, String passwordEntity);

    boolean existsByDniEntity(String dniEntity);
    boolean existsByEmailEntity(String emailEntity);
    boolean existsByPhoneEntity(String phoneEntity);
}
