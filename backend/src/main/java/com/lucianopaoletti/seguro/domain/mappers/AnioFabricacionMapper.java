package com.lucianopaoletti.seguro.domain.mappers;

import org.mapstruct.Mapper;

import com.lucianopaoletti.seguro.domain.AnioFabricacion;

@Mapper(componentModel = "spring")
public interface AnioFabricacionMapper {
	AnioFabricacion toDomain(com.lucianopaoletti.seguro.repositories.entities.AnioFabricacion entity);
}
