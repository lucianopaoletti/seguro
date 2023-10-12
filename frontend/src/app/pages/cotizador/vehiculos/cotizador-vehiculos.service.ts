import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';

import { Marca } from './types/marca.type';

@Injectable()
export class CotizadorVehiculosService {
  constructor(private http: HttpClient) {}

  getMarcas() {
    return this.http.get<Marca[]>(`${environment.apiUrl}/marca`);
  }
}
