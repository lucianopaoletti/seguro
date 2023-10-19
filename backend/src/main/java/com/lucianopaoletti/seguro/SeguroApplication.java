package com.lucianopaoletti.seguro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Seguro", description = "API de aseguradora"))
public class SeguroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguroApplication.class, args);
	}

}
