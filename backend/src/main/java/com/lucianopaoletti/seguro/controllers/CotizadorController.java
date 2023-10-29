package com.lucianopaoletti.seguro.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.domain.Marca;
import com.lucianopaoletti.seguro.domain.Modelo;
import com.lucianopaoletti.seguro.services.MarcaService;
import com.lucianopaoletti.seguro.services.ModeloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@Tag(name = "Cotizador", description = "Endpoints necesarios para cotizador")
@RestController
@RequestMapping("/cotizador/vehiculos")
public class CotizadorController {
	
	MarcaService marcaService;
	ModeloService modeloService;
	
	@Autowired
	public CotizadorController(MarcaService marcaService, ModeloService modeloService) {
		this.marcaService = marcaService;
		this.modeloService = modeloService;
	}
	
	@Operation(summary = "Obtiene todas las marcas")
	@GetMapping("/marca")
	public Collection<Marca> getMarcas() {
		return this.marcaService.getMarcas();
	}
	
	@Operation(summary = "Obtiene los modelos de una marca")
	@GetMapping("/modelo")
	public Collection<Modelo> getModelos(
			@Parameter(name = "marca", description = "ID de la marca", required = true)
			@RequestParam int marca
		) {
		return this.modeloService.getModelos(marca);
	}

}
