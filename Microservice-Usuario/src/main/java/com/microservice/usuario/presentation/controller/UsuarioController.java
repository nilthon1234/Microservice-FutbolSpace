package com.microservice.usuario.presentation.controller;

import com.microservice.usuario.persistence.models.Usuario;
import com.microservice.usuario.presentation.dto.UsuarioDto;
import com.microservice.usuario.service.http.response.ResponseUsuario;
import com.microservice.usuario.service.interfaces.IUsuarioService;
import com.microservice.usuario.utils.ResponseUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping("/client/list-access")
    public List<UsuarioDto> listAccEstado( ){
        return usuarioService.listByState();
    }

    @GetMapping("/usu/{dniEntity}")
    public ResponseEntity<?> searchDniUsuario(@PathVariable String dniEntity){
        return ResponseEntity.ok(usuarioService.findCampoFutbolByUsuario(dniEntity));
    }
    @PostMapping("/usu/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody UsuarioDto myDto){
        return ResponseEntity.ok(usuarioService.register(myDto));
    }

    @PostMapping("/usu/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDto mydto){
        Map<String, String> response = usuarioService.login(mydto.getDni(), mydto.getPassword());
        return ResponseUsuarioUtil.generateLoginResponse(response);
    }
    
    @PutMapping("/admin/actus/{idUsu}")
    public ResponseEntity<Map<String, String>> actucUuario(@PathVariable Long idUsu, @RequestBody UsuarioDto dto){
    	Map<String, String> responseMap = new HashMap<>();
    	if(idUsu != null) {
    		usuarioService.actusAccesUsu(idUsu, dto);
    		responseMap.put("message", "Esado Actualizado");
    		return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    	}else {
    		responseMap.put("message", "Error al Actualizar: " + idUsu);
    		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
    	}
    	
    }
    
    @GetMapping("/admin/allList")
    public List<UsuarioDto> lisAllUsuario(){
    	return usuarioService.AllListUsua();
    }
}
