package com.microservice.securityApp.service.implementation;

import com.microservice.securityApp.persistence.models.Administrator;
import com.microservice.securityApp.persistence.repository.IAdministratorRepository;
import com.microservice.securityApp.presentation.dto.AdministratorDto;
import com.microservice.securityApp.service.http.AuthRequest;
import com.microservice.securityApp.service.interfaces.IAdministratorService;
import com.microservice.securityApp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImple implements IAdministratorService {
    @Autowired
    private IAdministratorRepository administratorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public String saveAdmin(Administrator administrator) {
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
         Administrator sistemaString = administratorRepository.save(administrator);
         System.out.println("Administrator saved: " +  sistemaString);
         return "user added to the System";
    }

    @Override
    public AdministratorDto generateToken(String email) {

        String token = jwtUtils.generateToken(email);
        System.out.println("Token generado: " + token);
        AdministratorDto dto = new AdministratorDto();
        dto.setToken(token);
        return dto;
    }

    @Override
    public void validateToken(String token) {
        jwtUtils.validateToken(token);
    }
}
