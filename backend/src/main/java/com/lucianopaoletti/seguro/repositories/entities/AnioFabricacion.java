package com.lucianopaoletti.seguro.repositories.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
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
@Table(name = "versiones_anios_fabricacion")
@Data
@NoArgsConstructor
public class AnioFabricacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer anio;

	@Column(name = "suma_asegurada")
	private BigDecimal sumaAsegurada;

	@Column(name = "suma_asegurada_0km")
	private BigDecimal sumaAsegurada0km;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "version_id", referencedColumnName = "id")
	private Version version;
	
	public AnioFabricacion(com.lucianopaoletti.seguro.domain.AnioFabricacion anio) {
		this.id = anio.id();
		this.anio = anio.anio();
		this.sumaAsegurada = anio.sumaAsegurada();
		this.sumaAsegurada0km = anio.sumaAsegurada0km();
	}

}
