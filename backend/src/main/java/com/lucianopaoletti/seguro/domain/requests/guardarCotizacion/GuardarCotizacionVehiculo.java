package com.lucianopaoletti.seguro.domain.requests.guardarCotizacion;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GuardarCotizacionVehiculo(
		@NotNull(message = "Vehiculo - La marca es obligatoria")
		@Min(value = 1, message = "Vehiculo - La marca ingresada es inválida")
		String marcaId, 
		
		@NotNull(message = "Vehiculo - El modelo es obligatorio")
		@Min(value = 1, message = "Vehiculo - El modelo ingresado es inválido")
		String modeloId, 
		
		@NotNull(message = "Vehiculo - La versión es obligatoria")
		@Min(value = 1, message = "Vehiculo - La versión ingresada es inválida")
		String versionId, 
		
		@NotNull(message = "Vehiculo - El año es obligatorio")
		@Min(value = 1, message = "Vehiculo - El año ingresado es inválido")
		String anioId) {

}
