package com.lucianopaoletti.seguro.domain;

import java.math.BigDecimal;

public record Beneficio (int id, String nombre, BigDecimal tasa) {}
