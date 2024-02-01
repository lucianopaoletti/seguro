package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Beneficio;

@Repository
public interface BeneficioRepository extends ListCrudRepository<Beneficio, Integer> {

}
