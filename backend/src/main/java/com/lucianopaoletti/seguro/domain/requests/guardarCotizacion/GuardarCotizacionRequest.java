package com.lucianopaoletti.seguro.domain.requests.guardarCotizacion;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GuardarCotizacionRequest(
		@Valid
		GuardarCotizacionVehiculo vehiculo,

		@Valid
		List<GuardarCotizacionCobertura> coberturas,
		
		@NotNull(message = "La cobertura seleccionada es obligatoria")
		@Min(value = 1, message = "La cobertura seleccionada es inválida")
		Integer coberturaSeleccionada,

		@Valid
		GuardarCotizacionAsegurado asegurado) {

}
