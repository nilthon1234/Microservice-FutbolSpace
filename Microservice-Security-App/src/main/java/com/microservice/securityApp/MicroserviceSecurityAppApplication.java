package com.microservice.securityApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSecurityAppApplication.class, args);
	}

}
