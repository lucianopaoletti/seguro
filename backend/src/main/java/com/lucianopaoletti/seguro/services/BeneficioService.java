package com.lucianopaoletti.seguro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.repositories.BeneficioRepository;

@Service
public class BeneficioService {
	
	BeneficioRepository beneficioRepository;
	
	@Autowired
	public BeneficioService(BeneficioRepository beneficioRepository) {
		this.beneficioRepository = beneficioRepository;
	}
	
	public List<Beneficio> getBeneficios() {
		var beneficios = new ArrayList<Beneficio>();
		
		var iterator = this.beneficioRepository.findAll().iterator();
		while (iterator.hasNext()) {
			beneficios.add(this.convertToDto(iterator.next()));
		}
		
		return beneficios;
	}
	
	private Beneficio convertToDto(com.lucianopaoletti.seguro.repositories.entities.Beneficio beneficio) {
		return new Beneficio(beneficio.getId(), beneficio.getNombre(), beneficio.getTasa());
	}

}
