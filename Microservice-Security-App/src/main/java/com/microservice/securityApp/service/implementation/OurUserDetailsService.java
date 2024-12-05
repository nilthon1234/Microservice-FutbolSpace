package com.microservice.securityApp.service.implementation;

import com.microservice.securityApp.configuration.security.CustomAdminDetails;
import com.microservice.securityApp.persistence.models.Administrator;
import com.microservice.securityApp.persistence.repository.IAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class OurUserDetailsService implements UserDetailsService {
    @Autowired
    private IAdministratorRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Administrator> administrator = repository.findByEmail(username);
        return administrator.map(CustomAdminDetails::new).orElseThrow(() -> new UsernameNotFoundException("admin not found with name: " + username));
    }
}
