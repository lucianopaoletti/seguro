package com.lucianopaoletti.seguro.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.domain.Cobertura;
import com.lucianopaoletti.seguro.domain.CoberturaCotizada;
import com.lucianopaoletti.seguro.domain.Vehiculo;
import com.lucianopaoletti.seguro.domain.exceptions.RequestDataNotFoundException;
import com.lucianopaoletti.seguro.domain.requests.CotizarCoberturasRequest;

@Service
public class CotizadorService {
	
	// --------------------------------------------------------------------------------------------------
	// Atributos
	
	private final static Logger logger = LoggerFactory.getLogger(CotizadorService.class);
	
	private MarcaService marcaService;
	private ModeloService modeloService;
	private VersionService versionService;
	private AnioFabricacionService afService;
	private CoberturaService coberturaService;
	
	// --------------------------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public CotizadorService(MarcaService marcaService,
			ModeloService modeloService,
			VersionService versionService,
			AnioFabricacionService afService, CoberturaService coberturaService) {
		this.marcaService = marcaService;
		this.modeloService = modeloService;
		this.versionService = versionService;
		this.afService = afService;
		this.coberturaService = coberturaService;
	}
	
	// --------------------------------------------------------------------------------------------------
	// Metodos publicos
	
	public List<CoberturaCotizada> cotizarCoberturas(CotizarCoberturasRequest request) throws RequestDataNotFoundException {
		logger.info("Cotizar coberturas");
		
		var vehiculo = this.loadVehiculo(request);
		return this.coberturaService.getCoberturas()
				.stream()
				.map(c -> this.cotizarCobertura(c, vehiculo))
				.toList();
	}
	
	// --------------------------------------------------------------------------------------------------
	// Metodos privados
	
	private Vehiculo loadVehiculo(CotizarCoberturasRequest request) throws RequestDataNotFoundException {
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
		
		return new Vehiculo(null, marca, modelo, version, anio);
	}
	
	private CoberturaCotizada cotizarCobertura(Cobertura cobertura, Vehiculo vehiculo) {
		var coberturaCotizada = new CoberturaCotizada(cobertura);
		coberturaCotizada.setPrecio(this.calcularPrecioCobertura(cobertura, vehiculo));
		return coberturaCotizada;
	}
	
	private BigDecimal calcularPrecioCobertura(Cobertura cobertura, Vehiculo vehiculo) {
		return cobertura.getBeneficios()
			.stream()
			.map(b -> this.calcularPrecioBeneficio(b, vehiculo))
			.reduce(BigDecimal.ZERO, (subtotal, precioBeneficio) -> subtotal.add(precioBeneficio));
	}
	
	private BigDecimal calcularPrecioBeneficio(Beneficio beneficio, Vehiculo vehiculo) {
		return beneficio.tasa()
				.multiply(vehiculo.anio().sumaAsegurada())
				.divide(new BigDecimal(100))
				.multiply(new BigDecimal(0.01))
				.setScale(2, RoundingMode.HALF_EVEN);
	}

}
