package com.lucianopaoletti.seguro.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Modelo;
import com.lucianopaoletti.seguro.domain.mappers.ModeloMapper;
import com.lucianopaoletti.seguro.repositories.ModeloRepository;

@Service
public class ModeloService {

	// --------------------------------------------------------------------------------
	// Atributos

	private ModeloRepository repository;
	private ModeloMapper mapper;

	// --------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public ModeloService(ModeloRepository repository,ModeloMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// --------------------------------------------------------------------------------
	// Metodos

	public Collection<Modelo> getModelos(int marcaId) {
		return this.repository.findByMarcaId(marcaId)
				.stream()
				.map(this.mapper::toDomain)
				.toList();
	}

	public Optional<Modelo> getModelo(int id) {
		return this.repository.findById(id)
				.map(this.mapper::toDomain);
	}

}
