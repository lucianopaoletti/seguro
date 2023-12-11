package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Cobertura;

@Repository
public interface CoberturaRepository extends CrudRepository<Cobertura, Integer> {

}
