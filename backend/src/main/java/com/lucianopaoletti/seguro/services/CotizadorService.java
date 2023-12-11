package com.lucianopaoletti.seguro.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Cobertura;
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
	
	public List<Cobertura> cotizarCoberturas(Vehiculo vehiculo) {
		logger.info("Cotizar coberturas");
		
		return this.coberturaService.getCoberturas();
	}

}
