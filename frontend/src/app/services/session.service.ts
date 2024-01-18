import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  // ----------------------------------------------------------------
  // Atributos

  private readonly TOKEN_KEY = 'token';

  // ----------------------------------------------------------------
  // Metodos

  getToken() {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  setToken(token: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
  }
}
