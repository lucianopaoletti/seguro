package com.lucianopaoletti.seguro.repositories.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "coberturas")
@Data
public class Cobertura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;

	private String nombre;
	
	@OneToMany(mappedBy = "cobertura")
	private List<CoberturaBeneficio> beneficioIntermedio;

}
