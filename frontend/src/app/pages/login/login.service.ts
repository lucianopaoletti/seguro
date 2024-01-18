import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';

@Injectable()
export class LoginService {
  // ------------------------------------------------------------------------------
  // Constructor
  constructor(private httpService: HttpClient) {}

  // ------------------------------------------------------------------------------
  // Atributos

  private readonly API_URL = `${environment.apiUrl}/login`;

  // ------------------------------------------------------------------------------
  // Metodos

  public login(username: string, password: string) {
    return this.httpService.post<{ token: string }>(this.API_URL, {
      username: username,
      password: password,
    });
  }
}
