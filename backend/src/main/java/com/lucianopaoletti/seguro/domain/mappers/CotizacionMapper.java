package com.lucianopaoletti.seguro.domain.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lucianopaoletti.seguro.domain.Asegurado;
import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.domain.CoberturaCotizada;
import com.lucianopaoletti.seguro.domain.Cotizacion;
import com.lucianopaoletti.seguro.domain.Vehiculo;

/**
 * Mapper de MapStruct para convertir clase entity
 * {@link com.lucianopaoletti.seguro.repositories.entities.cotizacion.Cotizacion}
 *  a clase de dominio
 * {@link com.lucianopaoletti.seguro.domain.Cotizacion},
 * 
 * <p>
 * No se mappea en sentido inverso porque los entities requieren una estructura adicional
 * para persistir los datos en base. Usar MapStruct para esto seria forzar la libreria
 * a hacer algo del que no es su proposito original.
 * 
 * <p>
 * La logica de clase de dominio a entity se encuentra en: 
 * {@link com.lucianopaoletti.seguro.repositories.entities.cotizacion.Cotizacion#Cotizacion}
 *
 */
@Mapper(componentModel = "spring")
public interface CotizacionMapper {
	@Mapping(target = "vehiculo", source = "vehiculos")
	@Mapping(target = "asegurado", source = "asegurados")
	Cotizacion toDomain(com.lucianopaoletti.seguro.repositories.entities.cotizacion.Cotizacion entity);

	// Vehiculo

	@Mapping(target = "anio", source = "anioFabricacion")
	Vehiculo toDomain(com.lucianopaoletti.seguro.repositories.entities.cotizacion.CotizacionVehiculo entity);

	default Vehiculo lstVehiculoToDomain(
			List<com.lucianopaoletti.seguro.repositories.entities.cotizacion.CotizacionVehiculo> vehiculos) {
		return toDomain(vehiculos.get(0));
	}

	// Asegurado

	Asegurado toDomain(com.lucianopaoletti.seguro.repositories.entities.cotizacion.CotizacionAsegurado entity);

	default Asegurado lstAseguradoToDomain(
			List<com.lucianopaoletti.seguro.repositories.entities.cotizacion.CotizacionAsegurado> asegurados) {
		return toDomain(asegurados.get(0));
	}

	// Cobertura

	@Mapping(target = "numero", source = "cobertura.numero")
	@Mapping(target = "nombre", source = "cobertura.nombre")
	CoberturaCotizada toDomain(com.lucianopaoletti.seguro.repositories.entities.cotizacion.CotizacionCobertura entity);

	// Beneficio
	@Mapping(target = "nombre", source = "beneficio.nombre")
	Beneficio toDomain(com.lucianopaoletti.seguro.repositories.entities.cotizacion.CotizacionCoberturaBeneficio entity);
}
