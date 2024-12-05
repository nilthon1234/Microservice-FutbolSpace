package com.microservice.securityApp.service.interfaces;

import com.microservice.securityApp.persistence.models.Administrator;
import com.microservice.securityApp.presentation.dto.AdministratorDto;
import com.microservice.securityApp.service.http.AuthRequest;

public interface IAdministratorService {
    String saveAdmin(Administrator administrator);
    AdministratorDto generateToken(String email);
    void  validateToken(String token);
}
