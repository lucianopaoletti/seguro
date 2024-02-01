package com.lucianopaoletti.seguro.domain.mappers;

import org.mapstruct.Mapper;

import com.lucianopaoletti.seguro.domain.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	Usuario toDomain(com.lucianopaoletti.seguro.repositories.entities.Usuario entity);
}
