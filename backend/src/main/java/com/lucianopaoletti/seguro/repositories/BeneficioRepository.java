package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Beneficio;

@Repository
public interface BeneficioRepository extends CrudRepository<Beneficio, Integer> {

}
