package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lucianopaoletti.seguro.repositories.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
