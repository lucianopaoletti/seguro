import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

import { environment } from 'src/environments/environment';

import { Marca } from './types/marca.type';
import { Modelo } from './types/modelo.type';
import { Version } from './types/version.type';
import { AnioFabricacion } from './types/anio-fabricacion.type';
import { Cobertura } from './types/cobertura.type';
import { Beneficio } from './types/beneficio.type';

import { CotizarCoberturasRequest } from './types/requests/cotizar-coberturas-request.type';
import { GuardarCotizacionRequest } from './types/requests/guardar-cotizacion-request.type';
import { Cotizacion } from './types/cotizacion.type';
import { Page } from 'src/app/types/page.type';

@Injectable()
export class VehiculosService {
  // ------------------------------------------------------------------------------
  // Constructor

  constructor(private http: HttpClient) {}

  // ------------------------------------------------------------------------------
  // Atributos

  private readonly API_URL = `${environment.apiUrl}/cotizador/vehiculos/`;

  // ------------------------------------------------------------------------------
  // Metodos

  getMarcas() {
    return this.http.get<Marca[]>(`${this.API_URL}marcas`);
  }

  getModelos(marcaId: number) {
    return this.http.get<Modelo[]>(`${this.API_URL}modelos?marca=${marcaId}`);
  }

  getVersiones(modeloId: number) {
    return this.http.get<Version[]>(
      `${this.API_URL}versiones?modelo=${modeloId}`
    );
  }

  getAniosFabricacion(versionId: number) {
    return this.http.get<AnioFabricacion[]>(
      `${this.API_URL}aniosFabricacion?version=${versionId}`
    );
  }

  cotizar(request: CotizarCoberturasRequest) {
    return this.http
      .post<{ coberturas: Cobertura[] }>(
        `${this.API_URL}cotizarCoberturas`,
        request
      )
      .pipe(map((r) => r.coberturas));
  }

  getBeneficios(): Observable<Beneficio[]> {
    return this.http.get<Beneficio[]>(`${this.API_URL}beneficios`);
  }

  guardarCotizacion(request: GuardarCotizacionRequest) {
    return this.http.post<{ id: number }>(
      `${this.API_URL}guardarCotizacion`,
      request
    );
  }

  getCotizaciones() {
    return this.http.get<Page<Cotizacion>>(`${this.API_URL}cotizaciones`).pipe(
      map(c => c)
    );
  }
}
