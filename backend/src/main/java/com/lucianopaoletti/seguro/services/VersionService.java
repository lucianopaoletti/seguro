package com.lucianopaoletti.seguro.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Version;
import com.lucianopaoletti.seguro.repositories.VersionRepository;

@Service
public class VersionService {

	// ----------------------------------------------------------------------------------
	// Atributos

	VersionRepository versionRepository;

	// ----------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public VersionService(VersionRepository versionRepository) {
		this.versionRepository = versionRepository;
	}

	// ----------------------------------------------------------------------------------
	// Metodos publicos

	public Collection<Version> getVersiones(int modeloId) {
		return this.versionRepository.findByModeloId(modeloId)
				.stream()
				.map(this::convertToDto)
				.toList();
	}

	public Optional<Version> getVersion(int id) {
		return this.versionRepository.findById(id)
				.map(this::convertToDto);
	}

	// ----------------------------------------------------------------------------------
	// Metodos privados

	private Version convertToDto(com.lucianopaoletti.seguro.repositories.entities.Version version) {
		return new Version(version.getId(), version.getNombre());
	}

}
