package com.microservice.usuario.service.interfaces;

import com.microservice.usuario.persistence.models.Usuario;
import com.microservice.usuario.presentation.dto.CampoFutbolDto;
import com.microservice.usuario.presentation.dto.UsuarioDto;
import com.microservice.usuario.service.http.response.ResponseUsuario;

import java.util.List;
import java.util.Map;

public interface IUsuarioService {
    List<UsuarioDto> listByState();
    ResponseUsuario findCampoFutbolByUsuario(String dniEntity);
    Usuario register(UsuarioDto usuarioDto);
    Map<String, String> login(String dniEntity, String passwordEntity);
    
    //Admin
    List<UsuarioDto> AllListUsua();
    Usuario actusAccesUsu(Long id, UsuarioDto usuDto);
    

}
