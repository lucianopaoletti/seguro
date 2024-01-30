package com.lucianopaoletti.seguro.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.authentication.domain.requests.EncryptPasswordRequest;
import com.lucianopaoletti.seguro.authentication.domain.requests.EncryptPasswordResponse;
import com.lucianopaoletti.seguro.authentication.domain.requests.GenerateJwtKeyResponse;
import com.lucianopaoletti.seguro.authentication.services.EncryptionService;
import com.lucianopaoletti.seguro.authentication.services.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Configuración", description = "Configuraciones para otros endpoints")
@RestController
@RequestMapping("/config")
public class ConfigurationController {
	
	// -------------------------------------------------------------------------
	// Atributos
	
	EncryptionService encryptionService;
	JwtService jwtService;
	
	// -------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public ConfigurationController(EncryptionService encryptionService, JwtService jwtService) {
		this.encryptionService = encryptionService;
		this.jwtService = jwtService;
	}
	
	// -------------------------------------------------------------------------
	// Metodos POST
	
	@Operation(summary = "Encripta una contraseña plana con BCrypt")
	@PostMapping("/encryptPassword")
	public EncryptPasswordResponse encryptPassword(@RequestBody EncryptPasswordRequest request) {
		var encryptedPassword = this.encryptionService.encrypt(request.plainPassword());
		return new EncryptPasswordResponse(encryptedPassword);
	}
	
	@Operation(summary = "Genera una nueva secret key", 
			description = "Genera una nueva secret key que es usada como base para tokens JWS. \n\n"
					+ "Como esta API de autenticación y la API gateway necesitan encriptar y desencriptar tokens "
					+ "para validar que todo este correcto, ambas APIs deben compartir un mismo secret key. \n\n"
					+ "Este metodo facilita la generación de dicha key para que se pueda configurar manualmente en ambos proyectos. "
					+ "Este metodo no guarda ni reemplaza ninguna secret key en archivo de configuraciones ni base de datos, "
					+ "solo imprime el resultado.")
	@PostMapping("/generateJwtSecretKey")
	public GenerateJwtKeyResponse generateJwtSecretKey() {
		var key = this.jwtService.generateSecretKey();
		return new GenerateJwtKeyResponse(key);
	}

}
