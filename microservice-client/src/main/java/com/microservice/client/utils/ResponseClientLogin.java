package com.microservice.client.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseClientLogin {

    public static ResponseEntity<?> responseloginAcc (Map<String, String> response){
        if (response.containsKey("error")){
            return new  ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else if (response.containsKey("access")) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        }
        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
