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
	
	@PostMapping("/encryptPassword")
	public EncryptPasswordResponse encryptPassword(@RequestBody EncryptPasswordRequest request) {
		var encryptedPassword = this.encryptionService.encrypt(request.plainPassword());
		return new EncryptPasswordResponse(encryptedPassword);
	}
	
	@PostMapping("/generateJwtSecretKey")
	public GenerateJwtKeyResponse generateJwtSecretKey() {
		var key = this.jwtService.generateSecretKey();
		return new GenerateJwtKeyResponse(key);
	}

}
