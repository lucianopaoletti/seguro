package com.lucianopaoletti.seguro.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Cobertura;

@Service
public class CotizadorService {
	
	final static Logger logger = LoggerFactory.getLogger(CotizadorService.class);
	
	CoberturaService coberturaService;
	
	@Autowired
	public CotizadorService(CoberturaService coberturaService) {
		this.coberturaService = coberturaService;
	}
	
	public Collection<Cobertura> cotizarCoberturas() {
		logger.info("Cotizar coberturas");
		
		return this.coberturaService.getCoberturas();
	}

}
