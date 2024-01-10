package com.lucianopaoletti.seguro.authentication.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.authentication.repositories.entities.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	Optional<Usuario> findByUsername(String username);

}
