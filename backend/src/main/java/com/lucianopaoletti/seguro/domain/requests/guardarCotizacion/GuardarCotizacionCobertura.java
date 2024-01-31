package com.lucianopaoletti.seguro.domain.requests.guardarCotizacion;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GuardarCotizacionCobertura(
		@NotNull(message = "Coberturas - El número de cobertura es obligatorio")
		@Min(value = 1, message = "Coberturas - El número de cobertura es inválido")
		Integer numero, 
		
		@Valid
		List<GuardarCotizacionBeneficio> beneficios, 
		
		@NotNull(message = "Coberturas - El precio es obligatorio")
		@Min(value = 1, message = "Coberturas - El precio es inválido")
		BigDecimal precio) {

}
