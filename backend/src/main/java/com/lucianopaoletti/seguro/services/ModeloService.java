package com.lucianopaoletti.seguro.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Modelo;
import com.lucianopaoletti.seguro.repositories.ModeloRepository;

@Service
public class ModeloService {
	
	private ModeloRepository modeloRepository;
	
	@Autowired
	public ModeloService(ModeloRepository modeloRepository) {
		this.modeloRepository = modeloRepository;
	}
	
	public Collection<Modelo> getModelos(int marcaId) {
		return this.modeloRepository.findByMarcaId(marcaId)
			.stream()
			.map(this::convertToDTO)
			.toList();
	}
	
	private Modelo convertToDTO(com.lucianopaoletti.seguro.repositories.entities.Modelo modelo) {
		return new Modelo(modelo.getId(), modelo.getNombre());
	}

}
