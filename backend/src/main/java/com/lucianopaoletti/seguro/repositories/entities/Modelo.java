package com.lucianopaoletti.seguro.repositories.entities;

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
@Table(name = "modelos")
@Data
@NoArgsConstructor
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;
	
	public Modelo(com.lucianopaoletti.seguro.domain.Modelo modelo) {
		this.id = modelo.id();
		this.nombre = modelo.nombre();
	}

}
