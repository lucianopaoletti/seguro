package com.lucianopaoletti.seguro.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.domain.Cobertura;

@Mapper(componentModel = "spring")
public interface CoberturaMapper {
	@Mapping(target = "beneficios", source = "beneficioIntermedio")
	Cobertura toDomain(com.lucianopaoletti.seguro.repositories.entities.Cobertura entity);
	
	@Mapping(target = "id", source = "beneficio.id")
	@Mapping(target = "nombre", source = "beneficio.nombre")
	@Mapping(target = "tasa", source = "beneficio.tasa")
	Beneficio toDomain(com.lucianopaoletti.seguro.repositories.entities.CoberturaBeneficio entity);
}
