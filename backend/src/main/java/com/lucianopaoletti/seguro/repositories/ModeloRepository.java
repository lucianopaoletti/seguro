package com.lucianopaoletti.seguro.repositories;

import java.util.Collection;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Modelo;

@Repository
public interface ModeloRepository extends ListCrudRepository<Modelo, Integer> {
	
	Collection<Modelo> findByMarcaId(int id);
	
}
