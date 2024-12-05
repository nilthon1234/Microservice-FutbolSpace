package com.microservice.securityApp.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.securityApp.persistence.models.Administrator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdministratorDto {
    private Long id;
    private String password;
    private String email;
    private String phone;
    private String role;

    private String token;

}

