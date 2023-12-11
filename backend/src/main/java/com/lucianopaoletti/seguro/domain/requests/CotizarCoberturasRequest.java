package com.lucianopaoletti.seguro.domain.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CotizarCoberturasRequest(
		@NotNull(message = "La marca es obligatoria")
		@Min(value = 1, message = "La marca ingresada es inválida")
		String marcaId, 
		
		@NotNull(message = "El modelo es obligatorio")
		@Min(value = 1, message = "El modelo ingresado es inválido")
		String modeloId, 
		
		@NotNull(message = "La versión es obligatoria")
		@Min(value = 1, message = "La versión ingresada es inválida")
		String versionId, 
		
		@NotNull(message = "El año es obligatorio")
		@Min(value = 1, message = "El año ingresado es inválido")
		String anioId) {
}
