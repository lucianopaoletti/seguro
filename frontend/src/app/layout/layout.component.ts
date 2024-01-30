import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';
import { UserService } from '../services/user.service';
import { delay, finalize } from 'rxjs';

@Component({
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent {
  // ---------------------------------------------------------------------------------------
  // Constructor

  constructor(
    private userService: UserService,
    private sessionService: SessionService,
    private router: Router
  ) {}

  // ---------------------------------------------------------------------------------------
  // Atributos

  isCollapsed = false;

  isFetchingUser = true;
  user$ = this.userService
    .getUserInfo()
    .pipe(
      finalize(() => (this.isFetchingUser = false))
    );

  // ---------------------------------------------------------------------------------------
  // Metodos

  public logout() {
    this.sessionService.clearToken();
    this.router.navigate(['/login']);
  }
}
