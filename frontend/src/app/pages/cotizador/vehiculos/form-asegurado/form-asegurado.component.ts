import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { FormAsegurado } from './types/form-asegurado.type';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-form-asegurado',
  templateUrl: './form-asegurado.component.html',
})
export class FormAseguradoComponent implements OnInit {
  // ------------------------------------------------------------------------------
  // Constructor

  constructor(private fb: FormBuilder) {}

  // ------------------------------------------------------------------------------
  // Atributos

  form: FormGroup = this._buildForm();
  isFormLoading = false;

  @Input()
  formErrorEvent!: Observable<string>;

  @Output()
  filledEvent = new EventEmitter<FormAsegurado>();

  ngOnInit(): void {
    this.formErrorEvent.subscribe({
      next: () => {
        this.isFormLoading = false;
      },
    });
  }

  // ------------------------------------------------------------------------------
  // Metodos privados

  private _buildForm() {
    return this.fb.group({
      nombre: [null, Validators.required],
      apellido: [null, Validators.required],
      email: [null, [Validators.email, Validators.required]],
    });
  }

  // ------------------------------------------------------------------------------
  // Metodos publicos

  submitForm() {
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
    this.filledEvent.emit(this.form.value);
  }
}
