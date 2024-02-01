package com.lucianopaoletti.seguro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Cobertura;
import com.lucianopaoletti.seguro.domain.mappers.CoberturaMapper;
import com.lucianopaoletti.seguro.repositories.CoberturaRepository;

@Service
public class CoberturaService {

	// --------------------------------------------------------------------------------------------------
	// Atributos

	private CoberturaRepository repository;
	private CoberturaMapper mapper;

	// --------------------------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public CoberturaService(CoberturaRepository coberturaRepository, CoberturaMapper mapper) {
		this.repository = coberturaRepository;
		this.mapper = mapper;
	}

	// --------------------------------------------------------------------------------------------------
	// Metodos

	public List<Cobertura> getCoberturas() {
		return this.repository.findAll()
				.stream()
				.map(this.mapper::toDomain)
				.toList();
	}

}
