package com.lucianopaoletti.seguro.domain.mappers;

import org.mapstruct.Mapper;

import com.lucianopaoletti.seguro.domain.Marca;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
	Marca toDomain(com.lucianopaoletti.seguro.repositories.entities.Marca entity);
}
