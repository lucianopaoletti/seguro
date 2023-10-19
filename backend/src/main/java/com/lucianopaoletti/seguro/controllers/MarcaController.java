package com.lucianopaoletti.seguro.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.domain.Marca;
import com.lucianopaoletti.seguro.services.MarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@Tag(name = "Marcas", description = "Endpoints de marcas")
@RestController
@RequestMapping("/marca")
public class MarcaController {
	
	MarcaService marcaService;
	
	@Autowired
	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	
	@Operation(summary = "Obtiene todas las marcas")
	@GetMapping("/")
	public Collection<Marca> getMarcas() {
		return this.marcaService.getMarcas();
	}

}
