package com.lucianopaoletti.seguro.repositories.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "beneficios")
@Data
@NoArgsConstructor
public class Beneficio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	private BigDecimal tasa;
	
	@OneToMany(mappedBy = "beneficio")
	private List<CoberturaBeneficio> coberturaIntermedio;
	
	public Beneficio(com.lucianopaoletti.seguro.domain.Beneficio beneficio) {
		this.id = beneficio.id();
		this.nombre = beneficio.nombre();
		this.tasa = beneficio.tasa();
	}

}
