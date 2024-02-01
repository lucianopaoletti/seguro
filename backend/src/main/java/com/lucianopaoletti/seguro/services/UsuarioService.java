package com.lucianopaoletti.seguro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Usuario;
import com.lucianopaoletti.seguro.domain.exceptions.RequestDataNotFoundException;
import com.lucianopaoletti.seguro.domain.mappers.UsuarioMapper;
import com.lucianopaoletti.seguro.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	// --------------------------------------------------------------------------------
	// Atributos
	
	private UsuarioRepository repository;
	private UsuarioMapper mapper;
	
	// --------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	// --------------------------------------------------------------------------------
	// Metodos
	
	public Usuario getUsuario(int id) throws RequestDataNotFoundException {
		return this.repository.findById(id)
			.stream()
			.map(this.mapper::toDomain)
			.findFirst()
			.orElseThrow(() -> new RequestDataNotFoundException("No se encontró el usuario"));
	}
}
