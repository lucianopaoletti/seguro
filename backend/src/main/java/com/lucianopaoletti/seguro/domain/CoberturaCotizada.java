package com.lucianopaoletti.seguro.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CoberturaCotizada extends Cobertura {
	BigDecimal precio;
	
	public CoberturaCotizada(Cobertura cobertura) {
		super(cobertura);
	}
	
	public CoberturaCotizada(Cobertura cobertura, BigDecimal precio) {
		super(cobertura);
		this.precio = precio;
	}
}
