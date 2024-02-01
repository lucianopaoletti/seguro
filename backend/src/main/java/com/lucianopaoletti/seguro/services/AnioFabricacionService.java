package com.lucianopaoletti.seguro.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.AnioFabricacion;
import com.lucianopaoletti.seguro.domain.mappers.AnioFabricacionMapper;
import com.lucianopaoletti.seguro.repositories.AnioFabricacionRepository;

@Service
public class AnioFabricacionService {

	// --------------------------------------------------------------------------------------------
	// Atributos

	private AnioFabricacionRepository repository;
	private AnioFabricacionMapper mapper;

	// --------------------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public AnioFabricacionService(AnioFabricacionRepository repository, AnioFabricacionMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// --------------------------------------------------------------------------------------------
	// Metodos

	public Collection<AnioFabricacion> getAniosFabricacion(int versionId) {
		return this.repository.findByVersionId(versionId)
				.stream()
				.map(this.mapper::toDomain)
				.toList();
	}

	public Optional<AnioFabricacion> getAnioFabricacion(int id) {
		return this.repository.findById(id)
				.map(this.mapper::toDomain);
	}

}
