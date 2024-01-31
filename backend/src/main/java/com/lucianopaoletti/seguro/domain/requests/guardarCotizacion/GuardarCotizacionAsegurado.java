package com.lucianopaoletti.seguro.domain.requests.guardarCotizacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record GuardarCotizacionAsegurado(
		@NotNull(message = "Asegurado - El nombre es obligatorio")
		String nombre, 
		
		@NotNull(message = "Asegurado - El apellido es obligatorio")
		String apellido, 
		
		@NotNull(message = "Asegurado - El E-mail es obligatorio")
		@Email(message = "Asegurado - El E-mail es invalido")
		String email) {
}
