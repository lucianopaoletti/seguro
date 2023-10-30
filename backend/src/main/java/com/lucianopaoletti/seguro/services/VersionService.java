package com.lucianopaoletti.seguro.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Version;
import com.lucianopaoletti.seguro.repositories.VersionRepository;

@Service
public class VersionService {

	VersionRepository versionRepository;

	@Autowired
	public VersionService(VersionRepository versionRepository) {
		this.versionRepository = versionRepository;
	}

	public Collection<Version> getVersiones(int modeloId) {
		return this.versionRepository.findByModeloId(modeloId)
				.stream()
				.map(this::convertToDto)
				.toList();
	}

	private Version convertToDto(com.lucianopaoletti.seguro.repositories.entities.Version version) {
		return new Version(version.getId(), version.getNombre());
	}

}
