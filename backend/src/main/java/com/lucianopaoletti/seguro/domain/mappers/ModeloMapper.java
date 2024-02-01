package com.lucianopaoletti.seguro.domain.mappers;

import org.mapstruct.Mapper;

import com.lucianopaoletti.seguro.domain.Modelo;

@Mapper(componentModel = "spring")
public interface ModeloMapper {
	Modelo toDomain(com.lucianopaoletti.seguro.repositories.entities.Modelo entity);
}
