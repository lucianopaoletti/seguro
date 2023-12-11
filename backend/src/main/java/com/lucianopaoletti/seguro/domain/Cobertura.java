package com.lucianopaoletti.seguro.domain;

import java.util.List;

public record Cobertura(int numero, String nombre, List<Beneficio> beneficios) {
}
