package com.lucianopaoletti.seguro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.repositories.MarcaRepository;
import com.lucianopaoletti.seguro.repositories.entities.Marca;

@RestController
@RequestMapping("/marca")
public class MarcaController {
	
	MarcaRepository marcaRepository;
	
	@Autowired
	public MarcaController(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}
	
	@GetMapping("")
	public Iterable<Marca> getMarcas() {
		return this.marcaRepository.findAll();
	}

}
