package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.lucianopaoletti.seguro.repositories.entities.Usuario;

public interface UsuarioRepository extends ListCrudRepository<Usuario, Integer> {

}
