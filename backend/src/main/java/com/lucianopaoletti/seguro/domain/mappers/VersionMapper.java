package com.lucianopaoletti.seguro.domain.mappers;

import org.mapstruct.Mapper;

import com.lucianopaoletti.seguro.domain.Version;

@Mapper(componentModel = "spring")
public interface VersionMapper {
	Version toDomain(com.lucianopaoletti.seguro.repositories.entities.Version entity);
}
