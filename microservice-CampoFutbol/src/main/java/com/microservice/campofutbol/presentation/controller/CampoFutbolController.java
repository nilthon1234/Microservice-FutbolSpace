package com.microservice.campofutbol.presentation.controller;

import com.microservice.campofutbol.persistence.models.CampoFutbol;
import com.microservice.campofutbol.presentation.dto.CampoFutbolDto;
import com.microservice.campofutbol.service.interfaces.ICampoFutbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/campo")
public class CampoFutbolController {
    @Autowired
    private ICampoFutbolService iCampoFutbolService;
    
    //Client
    
    @GetMapping("/client/list-habilitados")
    public List<CampoFutbolDto> listaHabilitadas(){
        return iCampoFutbolService.listHabilitado();
    }

    @GetMapping("/client/{id}")
    public List<CampoFutbolDto> listaIdCampoFutbol(@PathVariable long id){
        return iCampoFutbolService.searchById(id);
    }
    //usuario
    @PostMapping("/usu/add")
    public ResponseEntity<?> addCampoFutbol(@RequestBody CampoFutbolDto myDto){
        return ResponseEntity.ok(iCampoFutbolService.addCampoFutbol(myDto));
    }
    @PutMapping("/usu/actus/{idCampo}")
    public ResponseEntity<Map<String, String>> actusCampo(@RequestBody CampoFutbolDto campoFutbolDto, @PathVariable Long idCampo){
        Map<String, String> response = new HashMap<>();
        if (idCampo != null) {
                iCampoFutbolService.actusCampoUsua(idCampo, campoFutbolDto);
                response.put("message", "actualizado");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else {
                response.put("message", "ERROR");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usu/dni/{dniEntity}")
    public List<CampoFutbolDto> searchDni(@PathVariable long dniEntity){
        return iCampoFutbolService.searchByDni(dniEntity);
    }
    @GetMapping("/admin/list")
    public List<CampoFutbolDto> listAllCampo(){
        return iCampoFutbolService.allListCampo();
    }
    @PutMapping("/admin/actus/{idCampo}")
    public ResponseEntity<Map<String, String>> actusCampoAdmin(@PathVariable Long idCampo, @RequestBody CampoFutbolDto campoFutbolDto){
        Map<String, String> response = new HashMap<>();
        if (idCampo != null) {
            iCampoFutbolService.actusCampoadmin(idCampo, campoFutbolDto);
            response.put("message", "Estado Actualizado");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else {
            response.put("message", "Error al actualizar: " + idCampo);
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }



}
