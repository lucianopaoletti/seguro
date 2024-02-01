package com.lucianopaoletti.seguro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.domain.mappers.BeneficioMapper;
import com.lucianopaoletti.seguro.repositories.BeneficioRepository;

@Service
public class BeneficioService {
	
	// --------------------------------------------------------------------------------------------------
	// Atributos
	
	private BeneficioRepository repository;
	private BeneficioMapper mapper;
	
	// --------------------------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public BeneficioService(BeneficioRepository repository, BeneficioMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	// --------------------------------------------------------------------------------------------------
	// Metodos
	
	public List<Beneficio> getBeneficios() {
		return this.repository.findAll()
				.stream()
				.map(this.mapper::toDomain)
				.toList();
	}

}
