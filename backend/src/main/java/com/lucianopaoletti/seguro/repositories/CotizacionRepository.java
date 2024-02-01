package com.lucianopaoletti.seguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucianopaoletti.seguro.repositories.entities.cotizacion.Cotizacion;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer>{

}
