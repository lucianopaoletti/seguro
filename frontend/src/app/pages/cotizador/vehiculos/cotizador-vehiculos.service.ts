import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';

import { Marca } from './types/marca.type';
import { Modelo } from './types/modelo.type';
import { Version } from './types/version.type';
import { AnioFabricacion } from './types/anio-fabricacion.type';

@Injectable()
export class CotizadorVehiculosService {
  constructor(private http: HttpClient) {}

  private readonly API_URL = `${environment.apiUrl}/cotizador/vehiculos/`;

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

  cotizar(params: {
    marca: number;
    modelo: number;
    version: number;
    anio: number;
  }) {
    return this.http.post(`${this.API_URL}cotizarCoberturas`, {
      marcaId: params.marca,
      modeloId: params.modelo,
      versionId: params.version,
      anioId: params.anio,
    });
  }
}
