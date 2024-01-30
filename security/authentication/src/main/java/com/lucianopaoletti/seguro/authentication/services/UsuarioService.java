package com.lucianopaoletti.seguro.authentication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.authentication.domain.exceptions.IncorrectPasswordException;
import com.lucianopaoletti.seguro.authentication.domain.exceptions.UserNotFoundException;
import com.lucianopaoletti.seguro.authentication.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	// -------------------------------------------------------------------------
	// Atributos

	final static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	UsuarioRepository usuarioRepository;
	EncryptionService encryptionService;
	JwtService jwtService;

	// -------------------------------------------------------------------------
	// Constructores

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, EncryptionService encryptionService,
			JwtService jwtService) {
		this.usuarioRepository = usuarioRepository;
		this.encryptionService = encryptionService;
		this.jwtService = jwtService;
	}

	// -------------------------------------------------------------------------
	// Metodos

	public String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
		var usuario = this.usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("No se encontró el usuario"));

		var passwordMatches = this.encryptionService.matches(password, usuario.getPassword());
		if (!passwordMatches) {
			throw new IncorrectPasswordException("El usuario o la contraseña es incorrecta");
		}
		
		return this.jwtService.generateJws(usuario.getId());
	}

}
