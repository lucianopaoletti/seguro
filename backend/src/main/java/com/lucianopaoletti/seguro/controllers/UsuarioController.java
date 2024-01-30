package com.lucianopaoletti.seguro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.domain.Usuario;
import com.lucianopaoletti.seguro.domain.exceptions.RequestDataNotFoundException;
import com.lucianopaoletti.seguro.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario", description = "Endpoints para información de usuarios")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	// -----------------------------------------------------------------------------------------------
	// Atributos
	
	private UsuarioService usuarioService;
	
	// -----------------------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	// -----------------------------------------------------------------------------------------------
	// Metodos
	
	@GetMapping("/")
	public Usuario getUsuario(@RequestHeader("userId") int id) throws RequestDataNotFoundException {		
		return this.usuarioService.getUsuario(id);
	}

}
