package com.lucianopaoletti.seguro.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.AnioFabricacion;

@Repository
public interface AnioFabricacionRepository extends CrudRepository<AnioFabricacion, Integer> {

	Collection<AnioFabricacion> findByVersionId(int id);

}
