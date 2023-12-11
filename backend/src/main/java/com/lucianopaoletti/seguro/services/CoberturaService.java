package com.lucianopaoletti.seguro.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Cobertura;
import com.lucianopaoletti.seguro.repositories.CoberturaRepository;

@Service
public class CoberturaService {

	CoberturaRepository coberturaRepository;

	@Autowired
	public CoberturaService(CoberturaRepository coberturaRepository) {
		this.coberturaRepository = coberturaRepository;
	}

	public Collection<Cobertura> getCoberturas() {
		var coberturas = new ArrayList<Cobertura>();

		var iterator = this.coberturaRepository.findAll().iterator();
		while (iterator.hasNext()) {
			coberturas.add(this.convertToDto(iterator.next()));
		}

		return coberturas;
	}

	private Cobertura convertToDto(com.lucianopaoletti.seguro.repositories.entities.Cobertura cobertura) {
		return new Cobertura(cobertura.getNumero(), cobertura.getNombre());
	}

}
