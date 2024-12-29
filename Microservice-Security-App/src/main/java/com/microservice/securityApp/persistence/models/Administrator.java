package com.microservice.securityApp.persistence.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_Admin")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "este password no puede estar vacio")
    private String password;

    @Email
    @NotBlank(message = "este email no puede estar vacio")
    @Column(unique = true)
    private String email;

    @NotNull(message = "el phone no puede estar nulo")
    @Column(unique = true)
    @Pattern(regexp = "\\d{9}", message = "el telefono debe tener exactamente 9 digitos")
    private String phone;
    private String role;

}

