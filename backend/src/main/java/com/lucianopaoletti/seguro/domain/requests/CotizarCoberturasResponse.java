package com.lucianopaoletti.seguro.domain.requests;

import java.util.List;

import com.lucianopaoletti.seguro.domain.CoberturaCotizada;

public record CotizarCoberturasResponse(List<CoberturaCotizada> coberturas) {

}
