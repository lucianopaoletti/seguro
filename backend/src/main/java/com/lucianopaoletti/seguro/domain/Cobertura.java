package com.lucianopaoletti.seguro.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cobertura {
	int numero;
	String nombre;
	List<Beneficio> beneficios;
	
	public Cobertura(Cobertura cobertura) {
		this.numero = cobertura.numero;
		this.nombre = cobertura.nombre;
		this.beneficios = new ArrayList<>();
		this.beneficios.addAll(cobertura.beneficios);
	}
}
