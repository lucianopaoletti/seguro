package com.lucianopaoletti.seguro.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.authentication.domain.exceptions.IncorrectPasswordException;
import com.lucianopaoletti.seguro.authentication.domain.exceptions.UserNotFoundException;
import com.lucianopaoletti.seguro.authentication.domain.requests.LoginError;
import com.lucianopaoletti.seguro.authentication.domain.requests.LoginRequest;
import com.lucianopaoletti.seguro.authentication.domain.requests.LoginResponse;
import com.lucianopaoletti.seguro.authentication.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticación", description = "Endpoints para autenticación de un usuario")
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

	@Operation(summary = "Login con usuario y contraseña en texto plano.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login exitoso"),
			@ApiResponse(responseCode = "400", description = "Error en login, ya sea porque "
					+ "el usuario ingresado no existe "
					+ "o porque no matchea el usuario y la contraseña", content = {
							@Content(schema = @Schema(implementation = LoginError.class))
					})
	})
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request)
			throws UserNotFoundException, IncorrectPasswordException {
		var token = this.usuarioService.login(request.username(), request.password());
		return new LoginResponse(token);
	}

}
