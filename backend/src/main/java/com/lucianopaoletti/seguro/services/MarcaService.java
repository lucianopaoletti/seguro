package com.lucianopaoletti.seguro.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Marca;
import com.lucianopaoletti.seguro.repositories.MarcaRepository;

@Service
public class MarcaService {
	
	private MarcaRepository marcaRepository;
	
	@Autowired
	public MarcaService(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}
	
	public Collection<Marca> getMarcas() {
		List<Marca> marcas = new ArrayList<>();
		var iterator = this.marcaRepository.findAll().iterator();
		while(iterator.hasNext()) {
			var marca = iterator.next();
			marcas.add(this.convertToDTO(marca));
		}
		
		return marcas;
	}
	
	private Marca convertToDTO(com.lucianopaoletti.seguro.repositories.entities.Marca marca) {
		return new Marca(marca.getId(), marca.getNombre());
	}

}
