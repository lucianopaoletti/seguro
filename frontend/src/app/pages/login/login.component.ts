import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './login.service';
import { finalize } from 'rxjs';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  // -------------------------------------------------------------------------
  // Constructor

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private sessionService: SessionService,
    private router: Router
  ) {}

  // -------------------------------------------------------------------------
  // Atributos

  form: FormGroup = this._buildForm();
  isFormLoading = false;

  // -------------------------------------------------------------------------
  // Metodos privados

  private _buildForm(): FormGroup {
    return this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  // -------------------------------------------------------------------------
  // Metodos publicos

  public submitForm() {
    if (!this.form.valid) {
      Object.values(this.form.controls).forEach((control) => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });

      return;
    }

    this.isFormLoading = true;
    const formValue = this.form.value;

    this.loginService
      .login(formValue.username, formValue.password)
      .pipe(finalize(() => (this.isFormLoading = false)))
      .subscribe({
        next: (response) => {
          this.sessionService.setToken(response.token);
          this.router.navigate(['/'])
        },
      });
  }
}
