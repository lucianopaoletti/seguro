package com.lucianopaoletti.seguro.repositories.entities.cotizacion;

import java.math.BigDecimal;
import java.util.List;

import com.lucianopaoletti.seguro.domain.CoberturaCotizada;
import com.lucianopaoletti.seguro.repositories.entities.Cobertura;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cotizaciones_coberturas")
@Data
@NoArgsConstructor
public class CotizacionCobertura {

	// -------------------------------------------------------------------------------------------------
	// Atributos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cotizacion_id", referencedColumnName = "id")
	private Cotizacion cotizacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobertura_numero", referencedColumnName = "numero")
	private Cobertura cobertura;

	private BigDecimal precio;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cotizacionCobertura", cascade = CascadeType.ALL)
	private List<CotizacionCoberturaBeneficio> beneficios;

	// -------------------------------------------------------------------------------------------------
	// Constructores
	
	public CotizacionCobertura(CoberturaCotizada cobertura, Cotizacion cotizacionPadre) {
		this.cotizacion = cotizacionPadre;
		this.cobertura = new Cobertura(cobertura);
		this.precio = cobertura.getPrecio();
		
		this.beneficios = cobertura.getBeneficios()
				.stream()
				.map(b -> new CotizacionCoberturaBeneficio(b, this))
				.toList();
	}

}
