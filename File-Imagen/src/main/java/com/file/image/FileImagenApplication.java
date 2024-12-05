package com.file.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FileImagenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileImagenApplication.class, args);
	}

}
