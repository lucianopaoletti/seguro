package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Marca;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Integer>{

}
