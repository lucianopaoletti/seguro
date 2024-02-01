package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Cobertura;

@Repository
public interface CoberturaRepository extends ListCrudRepository<Cobertura, Integer> {

}
