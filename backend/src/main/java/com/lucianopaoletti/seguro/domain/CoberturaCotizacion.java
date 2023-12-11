package com.lucianopaoletti.seguro.domain;

import java.math.BigDecimal;

public record CoberturaCotizacion (BigDecimal prima, BigDecimal premio, BigDecimal cuota) {}
