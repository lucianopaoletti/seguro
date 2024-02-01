package com.lucianopaoletti.seguro.domain.mappers;

import org.mapstruct.Mapper;

import com.lucianopaoletti.seguro.domain.Beneficio;

@Mapper(componentModel = "spring")
public interface BeneficioMapper {
	Beneficio toDomain(com.lucianopaoletti.seguro.repositories.entities.Beneficio entity);
}
