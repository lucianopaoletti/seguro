package com.lucianopaoletti.seguro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.domain.Cobertura;
import com.lucianopaoletti.seguro.repositories.CoberturaRepository;
import com.lucianopaoletti.seguro.repositories.entities.CoberturaBeneficio;

@Service
public class CoberturaService {
	
	// --------------------------------------------------------------------------------------------------
	// Atributos

	CoberturaRepository coberturaRepository;
	
	// --------------------------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public CoberturaService(CoberturaRepository coberturaRepository) {
		this.coberturaRepository = coberturaRepository;
	}
	
	// --------------------------------------------------------------------------------------------------
	// Metodos publicos

	public List<Cobertura> getCoberturas() {
		var coberturas = new ArrayList<Cobertura>();

		var iterator = this.coberturaRepository.findAll().iterator();
		while (iterator.hasNext()) {
			coberturas.add(this.convertToDto(iterator.next()));
		}

		return coberturas;
	}
	
	// --------------------------------------------------------------------------------------------------
	// Metodos privados

	private Cobertura convertToDto(com.lucianopaoletti.seguro.repositories.entities.Cobertura cobertura) {
		var beneficios = cobertura.getBeneficioIntermedio()
				.stream()
				.map(CoberturaBeneficio::getBeneficio)
				.map(this::convertToDto)
				.toList();
		
		return new Cobertura(cobertura.getNumero(), cobertura.getNombre(), beneficios);
	}
	
	private Beneficio convertToDto(com.lucianopaoletti.seguro.repositories.entities.Beneficio beneficio) {
		return new Beneficio(beneficio.getId(), beneficio.getNombre(), beneficio.getTasa());
	}

}
