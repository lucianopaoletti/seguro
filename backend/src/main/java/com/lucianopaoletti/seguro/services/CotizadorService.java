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

@Service
public class CotizadorService {
	
	// --------------------------------------------------------------------------------------------------
	// Atributos
	
	final static Logger logger = LoggerFactory.getLogger(CotizadorService.class);
	
	CoberturaService coberturaService;
	
	// --------------------------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public CotizadorService(CoberturaService coberturaService) {
		this.coberturaService = coberturaService;
	}
	
	// --------------------------------------------------------------------------------------------------
	// Metodos publicos
	
	public List<CoberturaCotizada> cotizarCoberturas(Vehiculo vehiculo) {
		logger.info("Cotizar coberturas");
		
		var coberturas = this.coberturaService.getCoberturas()
				.stream()
				.map(c -> new CoberturaCotizada(c))
				.map(c -> {c.setPrecio(this.calcularPrecioCobertura(c, vehiculo)); return c;})
				.toList();
		
		return coberturas;
	}
	
	// --------------------------------------------------------------------------------------------------
	// Metodos privados
	
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
