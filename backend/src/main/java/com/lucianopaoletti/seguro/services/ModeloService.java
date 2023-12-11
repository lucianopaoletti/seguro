package com.lucianopaoletti.seguro.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Modelo;
import com.lucianopaoletti.seguro.repositories.ModeloRepository;

@Service
public class ModeloService {

	// --------------------------------------------------------------------------------
	// Atributos

	private ModeloRepository modeloRepository;

	// --------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public ModeloService(ModeloRepository modeloRepository) {
		this.modeloRepository = modeloRepository;
	}

	// --------------------------------------------------------------------------------
	// Metodos publicos

	public Collection<Modelo> getModelos(int marcaId) {
		return this.modeloRepository.findByMarcaId(marcaId)
				.stream()
				.map(this::convertToDTO)
				.toList();
	}

	public Optional<Modelo> getModelo(int id) {
		return this.modeloRepository.findById(id)
				.map(this::convertToDTO);
	}

	// --------------------------------------------------------------------------------
	// Metodos privados

	private Modelo convertToDTO(com.lucianopaoletti.seguro.repositories.entities.Modelo modelo) {
		return new Modelo(modelo.getId(), modelo.getNombre());
	}

}
