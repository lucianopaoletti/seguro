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
@Table(name = "versiones")
@Data
@NoArgsConstructor
public class Version {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modelo_id", referencedColumnName = "id")
	private Modelo modelo;
	
	public Version(com.lucianopaoletti.seguro.domain.Version version) {
		this.id = version.id();
		this.nombre = version.nombre();
	}

}
