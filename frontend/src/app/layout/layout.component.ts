import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';

@Component({
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent {
  // ---------------------------------------------------------------------------------------
  // Atributos

  isCollapsed = false;

  // ---------------------------------------------------------------------------------------
  // Constructor

  constructor(private sessionService: SessionService, private router: Router) {}

  // ---------------------------------------------------------------------------------------
  // Metodos

  public logout() {
    this.sessionService.clearToken();
    this.router.navigate(['/login']);
  }
}
