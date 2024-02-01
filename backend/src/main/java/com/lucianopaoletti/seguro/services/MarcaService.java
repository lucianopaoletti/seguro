package com.lucianopaoletti.seguro.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Marca;
import com.lucianopaoletti.seguro.domain.mappers.MarcaMapper;
import com.lucianopaoletti.seguro.repositories.MarcaRepository;

@Service
public class MarcaService {

	// ----------------------------------------------------------------------------
	// Atributos

	private MarcaRepository repository;
	private MarcaMapper mapper;

	// ----------------------------------------------------------------------------
	// Constructores

	@Autowired
	public MarcaService(MarcaRepository repository, MarcaMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// ----------------------------------------------------------------------------
	// Metodos

	public Collection<Marca> getMarcas() {
		return this.repository.findAll()
				.stream()
				.map(this.mapper::toDomain)
				.toList();
	}

	public Optional<Marca> getMarca(int id) {
		return this.repository.findById(id)
				.map(this.mapper::toDomain);
	}

}
