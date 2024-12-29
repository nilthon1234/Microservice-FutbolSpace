package com.microservice.securityApp.presentation.controller;

import com.microservice.securityApp.persistence.models.Administrator;
import com.microservice.securityApp.presentation.dto.AdministratorDto;
import com.microservice.securityApp.service.http.AuthRequest;
import com.microservice.securityApp.service.interfaces.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAdministratorService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addAdmin(@RequestBody Administrator admin){
        return service.saveAdmin(admin);
    }
    @PostMapping("/token")
    public ResponseEntity<AdministratorDto> getToken(@RequestBody AuthRequest authRequest){
     Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
             (authRequest.getEmail(),
             authRequest.getPassword()));
        if (authentication.isAuthenticated()){
           AdministratorDto tokenDto = service.generateToken(authRequest.getEmail());
           return ResponseEntity.ok(tokenDto);
        }else {
            throw new RuntimeException("ivalid Acces");
        }

    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token")String token){
        service.validateToken(token);
        return "Token is valid";
    }
}
