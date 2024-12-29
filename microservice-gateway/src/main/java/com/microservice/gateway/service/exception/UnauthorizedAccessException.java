package com.microservice.gateway.service.exception;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(){
        super("un authorized access to application");
    }
}
