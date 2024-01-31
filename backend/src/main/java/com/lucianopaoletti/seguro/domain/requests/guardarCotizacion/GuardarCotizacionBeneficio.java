package com.lucianopaoletti.seguro.domain.requests.guardarCotizacion;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GuardarCotizacionBeneficio(
		@NotNull(message = "Beneficios - El ID del beneficio es obligatorio")
		@Min(value = 1, message = "Beneficios - El ID del beneficio es inválido")
		Integer id, 
		
		@NotNull(message = "Beneficios - La tasa del beneficio es obligatoria")
		@Min(value = 0, message = "Coberturas - El precio es inválido")
		BigDecimal tasa) {

}
