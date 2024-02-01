package com.lucianopaoletti.seguro.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Cotizacion(Integer id,
		Vehiculo vehiculo,
		List<CoberturaCotizada> coberturas,
		Asegurado asegurado,

		LocalDateTime fechaGuardado,
		Usuario usuario,
		int coberturaSeleccionada) {

}
