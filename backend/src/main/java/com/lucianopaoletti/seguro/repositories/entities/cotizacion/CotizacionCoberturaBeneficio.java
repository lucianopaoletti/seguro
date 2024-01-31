package com.lucianopaoletti.seguro.repositories.entities.cotizacion;

import java.math.BigDecimal;

import com.lucianopaoletti.seguro.repositories.entities.Beneficio;

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
@Table(name = "cotizaciones_coberturas_beneficios")
@Data
@NoArgsConstructor
public class CotizacionCoberturaBeneficio {

	// -------------------------------------------------------------------------------------------------
	// Atributos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cotizacion_cobertura_id", referencedColumnName = "id")
	private CotizacionCobertura cotizacionCobertura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "beneficio_id", referencedColumnName = "id")
	private Beneficio beneficio;

	private BigDecimal tasa;

	// -------------------------------------------------------------------------------------------------
	// Constructores

	public CotizacionCoberturaBeneficio(com.lucianopaoletti.seguro.domain.Beneficio beneficio,
			CotizacionCobertura cotizacionCobertura) {
		this.cotizacionCobertura = cotizacionCobertura;
		this.beneficio = new Beneficio(beneficio);
		this.tasa = beneficio.tasa();
	}

}
