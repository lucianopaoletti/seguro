package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.cotizacion.Cotizacion;

@Repository
public interface CotizacionRepository extends CrudRepository<Cotizacion, Integer>{

}
