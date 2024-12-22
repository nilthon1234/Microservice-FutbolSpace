package com.microservice.client.service.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExeceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateKeyExeception(DuplicateKeyException ex){
        Map<String, String> errors = new HashMap<>();
        String message = ex.getMessage();
        if (message.contains("dni")){
            errors.put("dni", "El dni ya esta registrado");
        } else if (message.contains("phone")) {
            errors.put("phone", "el telefono ya esta registrado");
        } else if (message.contains("email")) {
            errors.put("email", "el email ya esta registrado");
        } else {
            errors.put("error", "Un Error de la clave duplicada");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }
}
