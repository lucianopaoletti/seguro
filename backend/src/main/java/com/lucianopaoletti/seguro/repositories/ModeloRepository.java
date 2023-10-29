package com.lucianopaoletti.seguro.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Modelo;

@Repository
public interface ModeloRepository extends CrudRepository<Modelo, Integer> {
	
	Collection<Modelo> findByMarcaId(int id);
	
}
