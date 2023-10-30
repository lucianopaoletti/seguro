import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';

import { Marca } from './types/marca.type';
import { Modelo } from './types/modelo.type';
import { Version } from './types/version.type';

@Injectable()
export class CotizadorVehiculosService {
  constructor(private http: HttpClient) {}

  private readonly API_URL = `${environment.apiUrl}/cotizador/vehiculos/`;

  getMarcas() {
    return this.http.get<Marca[]>(`${this.API_URL}marca`);
  }

  getModelos(marcaId: number) {
    return this.http.get<Modelo[]>(`${this.API_URL}modelo?marca=${marcaId}`);
  }

  getVersiones(modeloId: number) {
    return this.http.get<Version[]>(`${this.API_URL}version?modelo=${modeloId}`);
  }
}
