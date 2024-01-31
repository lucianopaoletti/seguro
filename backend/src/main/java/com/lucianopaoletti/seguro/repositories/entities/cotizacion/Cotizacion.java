package com.lucianopaoletti.seguro.repositories.entities.cotizacion;

import java.time.LocalDateTime;
import java.util.List;

import com.lucianopaoletti.seguro.repositories.entities.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "cotizaciones")
@Data
@NoArgsConstructor
public class Cotizacion {
	
	// ---------------------------------------------------------------------------------------------
	// Atributos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fecha_guardado")
	private LocalDateTime fechaGuardado;

	@Column(name = "cobertura_seleccionada")
	private Integer coberturaSeleccionada;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cotizacion", cascade = CascadeType.ALL)
	private List<CotizacionVehiculo> vehiculos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cotizacion", cascade = CascadeType.ALL)
	private List<CotizacionCobertura> coberturas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cotizacion", cascade = CascadeType.ALL)
	private List<CotizacionAsegurado> asegurados;
	
	// ---------------------------------------------------------------------------------------------
	// Constructores
	
	public Cotizacion(com.lucianopaoletti.seguro.domain.Cotizacion cotizacion) {
		this.fechaGuardado = cotizacion.fechaGuardado();
		this.usuario = new Usuario(cotizacion.usuario());
		this.coberturaSeleccionada = cotizacion.coberturaSeleccionada();
		
		this.vehiculos = List.of(new CotizacionVehiculo(cotizacion.vehiculo(), this));
		
		this.coberturas = cotizacion.coberturas()
				.stream()
				.map(c -> new CotizacionCobertura(c, this))
				.toList();
		
		this.asegurados = List.of(new CotizacionAsegurado(cotizacion.asegurado(), this));
	}

}
