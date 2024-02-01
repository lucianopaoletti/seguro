package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Marca;

@Repository
public interface MarcaRepository extends ListCrudRepository<Marca, Integer> {
}
