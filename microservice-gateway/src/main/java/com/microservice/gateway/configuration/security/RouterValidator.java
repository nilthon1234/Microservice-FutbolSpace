package com.microservice.gateway.configuration.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public  static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            //Microservice-CampoFutbol
            "/campo/client/",
            "/campo/usu/",
            //Microservice-Usuario
            "/usuario/usu/",
            "/usuario/client/",
            //Microservice-Client
            "/public/client/",
            //Microservice-Reserva
            "/reserva/client/",
            //Microservice-File
            "/file/",
            "/eureka"
    );
    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> openApiEndpoints
            .stream()
            .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
