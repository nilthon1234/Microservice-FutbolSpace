package com.microservice.reserva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroserviceReservaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReservaApplication.class, args);
	}

}
