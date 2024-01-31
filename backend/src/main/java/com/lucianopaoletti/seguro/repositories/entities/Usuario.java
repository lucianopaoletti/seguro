package com.lucianopaoletti.seguro.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {
	
	// -------------------------------------------------------------------------------------------------
	// Atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String username;

	private String nombre;
	
	// -------------------------------------------------------------------------------------------------
	// Constructores
	
	public Usuario(com.lucianopaoletti.seguro.domain.Usuario usuario) {
		this.id = usuario.id();
		this.username = usuario.username();
		this.nombre = usuario.nombre();
	}

}
