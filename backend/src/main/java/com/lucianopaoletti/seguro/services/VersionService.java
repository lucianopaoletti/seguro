package com.lucianopaoletti.seguro.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Version;
import com.lucianopaoletti.seguro.domain.mappers.VersionMapper;
import com.lucianopaoletti.seguro.repositories.VersionRepository;

@Service
public class VersionService {

	// ----------------------------------------------------------------------------------
	// Atributos

	private VersionRepository repository;
	private VersionMapper mapper;

	// ----------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public VersionService(VersionRepository repository, VersionMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// ----------------------------------------------------------------------------------
	// Metodos

	public Collection<Version> getVersiones(int modeloId) {
		return this.repository.findByModeloId(modeloId)
				.stream()
				.map(this.mapper::toDomain)
				.toList();
	}

	public Optional<Version> getVersion(int id) {
		return this.repository.findById(id)
				.map(this.mapper::toDomain);
	}

}
