package com.lucianopaoletti.seguro.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucianopaoletti.seguro.domain.AnioFabricacion;
import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.domain.Marca;
import com.lucianopaoletti.seguro.domain.Modelo;
import com.lucianopaoletti.seguro.domain.Vehiculo;
import com.lucianopaoletti.seguro.domain.Version;
import com.lucianopaoletti.seguro.domain.exceptions.RequestDataNotFoundException;
import com.lucianopaoletti.seguro.domain.requests.CotizarCoberturasRequest;
import com.lucianopaoletti.seguro.domain.requests.CotizarCoberturasResponse;
import com.lucianopaoletti.seguro.domain.requests.guardarCotizacion.GuardarCotizacionRequest;
import com.lucianopaoletti.seguro.domain.requests.guardarCotizacion.GuardarCotizacionResponse;
import com.lucianopaoletti.seguro.services.AnioFabricacionService;
import com.lucianopaoletti.seguro.services.BeneficioService;
import com.lucianopaoletti.seguro.services.CotizadorService;
import com.lucianopaoletti.seguro.services.GuardarCotizacionService;
import com.lucianopaoletti.seguro.services.MarcaService;
import com.lucianopaoletti.seguro.services.ModeloService;
import com.lucianopaoletti.seguro.services.VersionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cotizador", description = "Endpoints necesarios para cotizador")
@RestController
@RequestMapping("/cotizador/vehiculos")
public class CotizadorController {

	// -----------------------------------------------------------------------------------------------
	// Atributos

	MarcaService marcaService;
	ModeloService modeloService;
	VersionService versionService;
	AnioFabricacionService afService;
	CotizadorService cotizadorService;
	BeneficioService beneficioService;
	GuardarCotizacionService guardarCotizacionService;

	// -----------------------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public CotizadorController(
			MarcaService marcaService,
			ModeloService modeloService,
			VersionService versionService,
			AnioFabricacionService afService,
			CotizadorService cotizadorService,
			BeneficioService beneficioService,
			GuardarCotizacionService guardarCotizacionService) {
		this.marcaService = marcaService;
		this.modeloService = modeloService;
		this.versionService = versionService;
		this.afService = afService;
		this.cotizadorService = cotizadorService;
		this.beneficioService = beneficioService;
		this.guardarCotizacionService = guardarCotizacionService;

	}

	// -----------------------------------------------------------------------------------------------
	// Metodos GET

	@Operation(summary = "Obtiene todas las marcas")
	@GetMapping("/marcas")
	public Collection<Marca> getMarcas() {
		return this.marcaService.getMarcas();
	}

	@Operation(summary = "Obtiene los modelos de una marca")
	@GetMapping("/modelos")
	public Collection<Modelo> getModelos(
			@Parameter(name = "marca", description = "ID de la marca", required = true) @RequestParam int marca) {
		return this.modeloService.getModelos(marca);
	}

	@Operation(summary = "Obtiene las versiones de un modelo")
	@GetMapping("/versiones")
	public Collection<Version> getVersiones(
			@Parameter(name = "modelo", description = "ID del modelo", required = true) @RequestParam int modelo) {
		return this.versionService.getVersiones(modelo);
	}

	@Operation(summary = "Obtiene los años de fabricación de una versión")
	@GetMapping("/aniosFabricacion")
	public Collection<AnioFabricacion> getAniosFabricacion(
			@Parameter(name = "version", description = "ID de la versión", required = true) @RequestParam int version) {
		return this.afService.getAniosFabricacion(version);
	}

	@Operation(summary = "Obtiene todos los beneficios que pueden llegar a tener las coberturas")
	@GetMapping("/beneficios")
	public Collection<Beneficio> getBeneficios() {
		return this.beneficioService.getBeneficios();
	}

	// -----------------------------------------------------------------------------------------------
	// Metodos POST

	@PostMapping("/cotizarCoberturas")
	public CotizarCoberturasResponse cotizarCoberturas(@Valid @RequestBody CotizarCoberturasRequest request)
			throws NumberFormatException, RequestDataNotFoundException {
		var marca = this.marcaService
				.getMarca(Integer.valueOf(request.marcaId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró la marca"));

		var modelo = this.modeloService
				.getModelo(Integer.valueOf(request.modeloId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró el modelo"));

		var version = this.versionService
				.getVersion(Integer.valueOf(request.versionId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró la versión"));

		var anio = this.afService
				.getAnioFabricacion(Integer.valueOf(request.anioId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró el año"));

		var vehiculo = new Vehiculo(marca, modelo, version, anio);

		var coberturas = this.cotizadorService.cotizarCoberturas(vehiculo);

		return new CotizarCoberturasResponse(coberturas);
	}

	@PostMapping("/guardarCotizacion")
	public GuardarCotizacionResponse guardarCotizacion(
			@Valid @RequestBody GuardarCotizacionRequest request,
			@RequestHeader("userId") int userId) throws NumberFormatException, RequestDataNotFoundException {

		var id = this.guardarCotizacionService.guardarCotizacion(request, userId);

		return new GuardarCotizacionResponse(id);
	}

}
