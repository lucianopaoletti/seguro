package com.lucianopaoletti.seguro.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.authentication.domain.exceptions.IncorrectPasswordException;
import com.lucianopaoletti.seguro.authentication.domain.exceptions.UserNotFoundException;
import com.lucianopaoletti.seguro.authentication.domain.requests.LoginRequest;
import com.lucianopaoletti.seguro.authentication.domain.requests.LoginResponse;
import com.lucianopaoletti.seguro.authentication.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	// -------------------------------------------------------------------------
	// Atributos

	UsuarioService usuarioService;

	// -------------------------------------------------------------------------
	// Constructores

	@Autowired
	public AuthenticationController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	// -------------------------------------------------------------------------
	// Metodos POST

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) 
			throws UserNotFoundException, IncorrectPasswordException {
		var token = this.usuarioService.login(request.username(), request.password());
		return new LoginResponse(token);
	}

}
