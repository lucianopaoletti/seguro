package com.lucianopaoletti.seguro.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "marcas")
@Data
@NoArgsConstructor
public class Marca {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nombre;
	
	public Marca(com.lucianopaoletti.seguro.domain.Marca marca) {
		this.id = marca.id();
		this.nombre = marca.nombre();
	}
}
