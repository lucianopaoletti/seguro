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

@Entity
@Table(name = "beneficios")
@Data
public class Beneficio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	private BigDecimal tasa;
	
	@OneToMany(mappedBy = "beneficio")
	private List<CoberturaBeneficio> coberturaIntermedio;

}
