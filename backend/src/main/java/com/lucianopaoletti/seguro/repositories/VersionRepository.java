package com.lucianopaoletti.seguro.repositories;

import java.util.Collection;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.Version;

@Repository
public interface VersionRepository extends ListCrudRepository<Version, Integer> {
	
	Collection<Version> findByModeloId(int id);

}
