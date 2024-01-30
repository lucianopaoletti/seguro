package com.lucianopaoletti.seguro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Usuario;
import com.lucianopaoletti.seguro.domain.exceptions.RequestDataNotFoundException;
import com.lucianopaoletti.seguro.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	// --------------------------------------------------------------------------------
	// Atributos
	
	private UsuarioRepository usuarioRepository;
	
	// --------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	// --------------------------------------------------------------------------------
	// Metodos publicos
	
	public Usuario getUsuario(int id) throws RequestDataNotFoundException {
		return this.usuarioRepository.findById(id)
			.stream()
			.map(this::convertToDTO)
			.findFirst()
			.orElseThrow(() -> new RequestDataNotFoundException("No se encontró el usuario"));
	}
	
	// --------------------------------------------------------------------------------
	// Metodos privados

	private Usuario convertToDTO(com.lucianopaoletti.seguro.repositories.entities.Usuario usuario) {
		return new Usuario(usuario.getId(), usuario.getUsername(), usuario.getNombre());
	}
}
