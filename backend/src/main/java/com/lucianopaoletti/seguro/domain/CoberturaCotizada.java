package com.lucianopaoletti.seguro.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CoberturaCotizada extends Cobertura {
	BigDecimal precio;
	
	public CoberturaCotizada(Cobertura cobertura) {
		super(cobertura);
	}
}
