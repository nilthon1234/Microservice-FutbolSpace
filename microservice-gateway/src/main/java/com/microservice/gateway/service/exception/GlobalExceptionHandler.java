package com.microservice.gateway.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingAuthorizationHeaderException.class)
    public ResponseEntity<Object> handleMissingAuthHeader(MissingAuthorizationHeaderException ex) {
        return new ResponseEntity<>("Authorization token is required", HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Object> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
        return new ResponseEntity<>("Invalid or expired token", HttpStatus.UNAUTHORIZED);
    }
}
