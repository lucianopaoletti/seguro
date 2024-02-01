package com.lucianopaoletti.seguro.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.domain.Asegurado;
import com.lucianopaoletti.seguro.domain.Beneficio;
import com.lucianopaoletti.seguro.domain.CoberturaCotizada;
import com.lucianopaoletti.seguro.domain.Cotizacion;
import com.lucianopaoletti.seguro.domain.Usuario;
import com.lucianopaoletti.seguro.domain.Vehiculo;
import com.lucianopaoletti.seguro.domain.exceptions.RequestDataNotFoundException;
import com.lucianopaoletti.seguro.domain.mappers.CotizacionMapper;
import com.lucianopaoletti.seguro.domain.requests.guardarCotizacion.GuardarCotizacionBeneficio;
import com.lucianopaoletti.seguro.domain.requests.guardarCotizacion.GuardarCotizacionRequest;
import com.lucianopaoletti.seguro.repositories.CotizacionRepository;

@Service
public class CotizacionService {

	// --------------------------------------------------------------------------------------------------
	// Atributos

	final static Logger logger = LoggerFactory.getLogger(CotizadorService.class);

	MarcaService marcaService;
	ModeloService modeloService;
	VersionService versionService;
	AnioFabricacionService afService;

	CoberturaService coberturaService;
	BeneficioService beneficioService;

	UsuarioService usuarioService;

	private CotizacionRepository cotizacionRepository;
	private CotizacionMapper cotizacionMapper;

	// --------------------------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public CotizacionService(MarcaService marcaService,
			ModeloService modeloService,
			VersionService versionService,
			AnioFabricacionService afService,
			CoberturaService coberturaService,
			BeneficioService beneficioService,
			UsuarioService usuarioService,
			CotizacionRepository cotizacionRepository,
			CotizacionMapper cotizacionMapper) {
		this.marcaService = marcaService;
		this.modeloService = modeloService;
		this.versionService = versionService;
		this.afService = afService;

		this.coberturaService = coberturaService;
		this.beneficioService = beneficioService;

		this.usuarioService = usuarioService;

		this.cotizacionRepository = cotizacionRepository;
		this.cotizacionMapper = cotizacionMapper;
	}

	// --------------------------------------------------------------------------------------------------
	// Metodos publicos

	public int guardarCotizacion(GuardarCotizacionRequest request, int usuarioId)
			throws NumberFormatException, RequestDataNotFoundException {
		var cotizacion = this.loadCotizacion(request, usuarioId);
		var entity = new com.lucianopaoletti.seguro.repositories.entities.cotizacion.Cotizacion(cotizacion);
		this.cotizacionRepository.save(entity);
		return entity.getId();
	}
	
	public Page<Cotizacion> getCotizaciones(int pageNumber, int pageSize) {
		var sort = Sort.by("fechaGuardado").descending();
		var pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		return this.cotizacionRepository.findAll(pageable)
				.map(this.cotizacionMapper::toDomain);
	}

	// --------------------------------------------------------------------------------------------------
	// Metodos privados

	private Cotizacion loadCotizacion(GuardarCotizacionRequest request, int usuarioId)
			throws RequestDataNotFoundException {
		var usuario = this.loadUsuario(usuarioId);
		var vehiculo = this.loadVehiculo(request);
		var coberturas = this.loadCoberturas(request);
		var asegurado = this.loadAsegurado(request);

		return new Cotizacion(null,
				vehiculo,
				coberturas,
				asegurado,
				LocalDateTime.now(),
				usuario,
				request.coberturaSeleccionada());
	}

	private Usuario loadUsuario(int id) throws RequestDataNotFoundException {
		return this.usuarioService.getUsuario(id);
	}

	private Vehiculo loadVehiculo(GuardarCotizacionRequest request)
			throws NumberFormatException, RequestDataNotFoundException {
		var marca = this.marcaService
				.getMarca(Integer.valueOf(request.vehiculo().marcaId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró la marca"));

		var modelo = this.modeloService
				.getModelo(Integer.valueOf(request.vehiculo().modeloId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró el modelo"));

		var version = this.versionService
				.getVersion(Integer.valueOf(request.vehiculo().versionId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró la versión"));

		var anio = this.afService
				.getAnioFabricacion(Integer.valueOf(request.vehiculo().anioId()))
				.orElseThrow(() -> new RequestDataNotFoundException("No se encontró el año"));

		return new Vehiculo(null, marca, modelo, version, anio);
	}

	private List<CoberturaCotizada> loadCoberturas(GuardarCotizacionRequest request)
			throws RequestDataNotFoundException {
		var coberturas = this.coberturaService.getCoberturas();
		var beneficios = this.beneficioService.getBeneficios();

		List<CoberturaCotizada> coberturasCotizadas = new ArrayList<>();
		for (int i = 0; i < request.coberturas().size(); i++) {
			var coberturaRequest = request.coberturas().get(i);

			var cobertura = coberturas.stream()
					.filter(c -> c.getNumero() == coberturaRequest.numero())
					.findFirst()
					.orElseThrow(() -> new RequestDataNotFoundException(
							"No se encontró la cobertura número " + String.valueOf(coberturaRequest.numero())));

			var coberturaCotizada = new CoberturaCotizada(cobertura, coberturaRequest.precio());

			coberturaCotizada.setBeneficios(this.loadBeneficios(coberturaRequest.beneficios(), beneficios));
			coberturasCotizadas.add(coberturaCotizada);
		}

		return coberturasCotizadas;
	}

	private List<Beneficio> loadBeneficios(List<GuardarCotizacionBeneficio> beneficiosRequest,
			List<Beneficio> beneficios) throws RequestDataNotFoundException {

		List<Beneficio> beneficiosLoaded = new ArrayList<>();
		for (int i = 0; i < beneficiosRequest.size(); i++) {
			var beneficioRequest = beneficiosRequest.get(i);

			var beneficio = beneficios.stream()
					.filter(b -> b.id() == beneficioRequest.id())
					.findFirst()
					.orElseThrow(() -> new RequestDataNotFoundException(
							"No se encontró el beneficio " + String.valueOf(beneficioRequest.id())));

			var beneficioLoaded = new Beneficio(beneficio.id(), beneficio.nombre(), beneficioRequest.tasa());
			beneficiosLoaded.add(beneficioLoaded);
		}

		return beneficiosLoaded;
	}

	private Asegurado loadAsegurado(GuardarCotizacionRequest request) {
		return new Asegurado(null,
				request.asegurado().nombre(),
				request.asegurado().apellido(),
				request.asegurado().email());
	}

}
