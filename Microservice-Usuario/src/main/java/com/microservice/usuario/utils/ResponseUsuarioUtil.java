package com.microservice.usuario.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseUsuarioUtil {

    public static ResponseEntity<?> generateLoginResponse(Map<String, String> response){
        if (response.containsKey("error")){
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else if (response.containsKey("not_access")) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else if (response.containsKey("success")) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
