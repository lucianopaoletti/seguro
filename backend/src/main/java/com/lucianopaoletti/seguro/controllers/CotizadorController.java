package com.lucianopaoletti.seguro.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.domain.AnioFabricacion;
import com.lucianopaoletti.seguro.domain.Marca;
import com.lucianopaoletti.seguro.domain.Modelo;
import com.lucianopaoletti.seguro.domain.Version;
import com.lucianopaoletti.seguro.services.AnioFabricacionService;
import com.lucianopaoletti.seguro.services.MarcaService;
import com.lucianopaoletti.seguro.services.ModeloService;
import com.lucianopaoletti.seguro.services.VersionService;

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
	VersionService versionService;
	AnioFabricacionService afService;

	@Autowired
	public CotizadorController(
			MarcaService marcaService,
			ModeloService modeloService,
			VersionService versionService,
			AnioFabricacionService afService) {
		this.marcaService = marcaService;
		this.modeloService = modeloService;
		this.versionService = versionService;
		this.afService = afService;
	}

	@Operation(summary = "Obtiene todas las marcas")
	@GetMapping("/marca")
	public Collection<Marca> getMarcas() {
		return this.marcaService.getMarcas();
	}

	@Operation(summary = "Obtiene los modelos de una marca")
	@GetMapping("/modelo")
	public Collection<Modelo> getModelos(
			@Parameter(name = "marca", description = "ID de la marca", required = true) @RequestParam int marca) {
		return this.modeloService.getModelos(marca);
	}

	@Operation(summary = "Obtiene las versiones de un modelo")
	@GetMapping("/version")
	public Collection<Version> getVersiones(
			@Parameter(name = "modelo", description = "ID del modelo", required = true) @RequestParam int modelo) {
		return this.versionService.getVersiones(modelo);
	}

	@Operation(summary = "Obtiene los años de fabricación de una versión")
	@GetMapping("/anioFabricacion")
	public Collection<AnioFabricacion> getAniosFabricacion(
			@Parameter(name = "version", description = "ID de la versión", required = true) @RequestParam int version) {
		return this.afService.getAniosFabricacion(version);
	}

}
