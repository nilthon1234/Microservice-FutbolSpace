package com.microservice.gateway.service.exception;

public class MissingAuthorizationHeaderException extends RuntimeException{
    public MissingAuthorizationHeaderException(){
        super("missing authorization header");
    }
}
