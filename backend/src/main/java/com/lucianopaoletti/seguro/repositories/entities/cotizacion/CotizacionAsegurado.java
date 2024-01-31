package com.lucianopaoletti.seguro.repositories.entities.cotizacion;

import com.lucianopaoletti.seguro.domain.Asegurado;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cotizaciones_asegurado")
@Data
@NoArgsConstructor
public class CotizacionAsegurado {

	// -------------------------------------------------------------------------------------------------
	// Atributos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cotizacion_id", referencedColumnName = "id")
	private Cotizacion cotizacion;

	private String nombre;

	private String apellido;

	private String email;

	// -------------------------------------------------------------------------------------------------
	// Constructores
	
	public CotizacionAsegurado(Asegurado asegurado, Cotizacion cotizacionPadre) {
		this.cotizacion = cotizacionPadre;
		this.nombre = asegurado.nombre();
		this.apellido = asegurado.apellido();
		this.email = asegurado.email();
	}
}
