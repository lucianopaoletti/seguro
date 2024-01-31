package com.lucianopaoletti.seguro.repositories.entities.cotizacion;

import java.math.BigDecimal;

import com.lucianopaoletti.seguro.domain.Vehiculo;
import com.lucianopaoletti.seguro.repositories.entities.AnioFabricacion;
import com.lucianopaoletti.seguro.repositories.entities.Marca;
import com.lucianopaoletti.seguro.repositories.entities.Modelo;
import com.lucianopaoletti.seguro.repositories.entities.Version;

import jakarta.persistence.Column;
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
@Table(name = "cotizaciones_vehiculos")
@Data
@NoArgsConstructor
public class CotizacionVehiculo {
	
	// -------------------------------------------------------------------------------------------------
	// Atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cotizacion_id", referencedColumnName = "id")
	private Cotizacion cotizacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modelo_id", referencedColumnName = "id")
	private Modelo modelo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "version_id", referencedColumnName = "id")
	private Version version;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "version_anio_fabricacion_id", referencedColumnName = "id")
	private AnioFabricacion anioFabricacion;
	
	@Column(name = "suma_asegurada")
	private BigDecimal sumaAsegurada;
	
	@Column(name = "suma_asegurada_0km")
	private BigDecimal sumaAsegurada0km;
	
	// -------------------------------------------------------------------------------------------------
	// Constructores
	
	public CotizacionVehiculo(Vehiculo vehiculo, Cotizacion cotizacionPadre) {
		this.cotizacion = cotizacionPadre;
		this.marca = new Marca(vehiculo.marca());
		this.modelo = new Modelo(vehiculo.modelo());
		this.version = new Version(vehiculo.version());
		this.anioFabricacion = new AnioFabricacion(vehiculo.anio());
		this.sumaAsegurada = this.anioFabricacion.getSumaAsegurada();
		this.sumaAsegurada0km = this.anioFabricacion.getSumaAsegurada0km();
	}

}
