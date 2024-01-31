package com.lucianopaoletti.seguro.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coberturas_beneficios")
@IdClass(CoberturaBeneficioId.class)
@Data
@NoArgsConstructor
public class CoberturaBeneficio {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "cobertura_numero", referencedColumnName = "numero")
	private Cobertura cobertura;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "beneficio_id", referencedColumnName = "id")
	private Beneficio beneficio;

}
