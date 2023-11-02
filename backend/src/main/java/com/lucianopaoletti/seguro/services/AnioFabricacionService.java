package com.lucianopaoletti.seguro.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.AnioFabricacion;
import com.lucianopaoletti.seguro.repositories.AnioFabricacionRepository;

@Service
public class AnioFabricacionService {

	AnioFabricacionRepository afRepository;

	@Autowired
	public AnioFabricacionService(AnioFabricacionRepository afRepository) {
		this.afRepository = afRepository;
	}

	public Collection<AnioFabricacion> getAniosFabricacion(int versionId) {
		return this.afRepository.findByVersionId(versionId)
				.stream()
				.map(this::convertToDto)
				.toList();
	}

	public AnioFabricacion convertToDto(com.lucianopaoletti.seguro.repositories.entities.AnioFabricacion af) {
		return new AnioFabricacion(af.getId(), af.getAnio(), af.getSumaAsegurada(), af.getSumaAsegurada0km());
	}

}
