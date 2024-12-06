package com.microservice.usuario.service.implementation;

import com.microservice.usuario.persistence.models.Usuario;
import com.microservice.usuario.persistence.repository.IUsuarioRepository;
import com.microservice.usuario.presentation.dto.CampoFutbolDto;
import com.microservice.usuario.presentation.dto.ImagenFileDto;
import com.microservice.usuario.presentation.dto.UsuarioDto;
import com.microservice.usuario.service.http.response.ResponseUsuario;
import com.microservice.usuario.service.interfaces.IUsuarioService;
import com.microservice.usuario.utils.interfaces.CampoFutbolClient;
import com.microservice.usuario.utils.interfaces.FileImagenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private CampoFutbolClient campoFutbolClient;
    @Autowired
    private FileImagenClient fileImagenClient;

    @Override
    public List<UsuarioDto> listByState( ) {
        List<Usuario> list = usuarioRepository.findByEstadoEntity(Usuario.Estado.Acces);
        return list.stream()
                .map(usu -> {
                    UsuarioDto mydto = new UsuarioDto();
                    mydto.setName(usu.getEmailEntity());
                    mydto.setDni(usu.getDniEntity());
                    mydto.setLastname(usu.getLastnameEntity());
                    mydto.setPhone(usu.getPhoneEntity());
                    mydto.setEmail(usu.getEmailEntity());
                    return mydto;
                })
                .collect(Collectors.toList());


    }

    @Override
    public ResponseUsuario findCampoFutbolByUsuario(long dniEntity) {
        Usuario usuario = usuarioRepository.findByDniEntity(dniEntity).orElse(new Usuario());
        List<CampoFutbolDto> campoFutbolDtos = campoFutbolClient.shearchDni(dniEntity);
        campoFutbolDtos.forEach(campoFutbolDto -> {
           List<ImagenFileDto> imagen = fileImagenClient.getListIdCampoFutbol(campoFutbolDto.getId());
           campoFutbolDto.setFileImagen(imagen);
        });
        return ResponseUsuario.builder()
                .dni(usuario.getDniEntity())
                .phone(usuario.getPhoneEntity())
                .name(usuario.getNameEntity())
                .CampoFutbol(campoFutbolDtos)
                .build();
    }

    @Override
    public Usuario register(UsuarioDto usuarioDto) {
        Usuario usu = new Usuario();
        usu.setNameEntity(usuarioDto.getName());
        usu.setLastnameEntity(usuarioDto.getLastname());
        usu.setEmailEntity(usuarioDto.getEmail());
        usu.setPasswordEntity(usuarioDto.getPassword());
        usu.setDniEntity(usuarioDto.getDni());
        usu.setPhoneEntity(usuarioDto.getPhone());
        return usuarioRepository.save(usu);
    }

    @Override
    public Map<String, String> login(long dniEntity, String passwordEntity) {
        Optional<Usuario> validar = usuarioRepository.findByDniEntityAndPasswordEntity(dniEntity, passwordEntity);
        Map<String, String> response = new HashMap<>();

        if (!validar.isPresent()){
            response.put("error", "dni o password incorrectos");
            return response;
        }
        Usuario usu = validar.get();
        if (usu.getEstadoEntity() != Usuario.Estado.Acces){
            response.put("not_access", "no  tiene acceso a esta cuenta: " + usu.getEmailEntity());
            return response;
        }

        response.put("success", "Login Exitoso: " + usu.getNameEntity());
        return response;
    }

	@Override
	public Usuario actusAccesUsu(Long id, UsuarioDto usuDto) {
		
		return usuarioRepository.findById(id)
				.map(actus -> {
					actus.setEstadoEntity(Usuario.Estado.valueOf(usuDto.getEstado()));
					return usuarioRepository.save(actus);
				})
		.orElseThrow(() -> new IllegalArgumentException("Id invalido: " + id));
	}

	@Override
	public List<UsuarioDto> AllListUsua() {
		return usuarioRepository.findAll().stream()
				.map(UsuarioDto::new)
				.collect(Collectors.toList());
	}
}
