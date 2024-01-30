import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { User } from '../types/user.type';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // ------------------------------------------------------------------------------
  // Constructor

  constructor(private http: HttpClient) {}

  // ------------------------------------------------------------------------------
  // Atributos

  private readonly API_URL = `${environment.apiUrl}/usuario/`;

  // ------------------------------------------------------------------------------
  // Metodos

  public getUserInfo() {
    return this.http.get<User>(`${this.API_URL}`);
  }

}
