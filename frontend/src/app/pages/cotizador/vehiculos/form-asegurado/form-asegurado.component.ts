import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { FormAsegurado } from './types/form-asegurado.type';

@Component({
  selector: 'app-form-asegurado',
  templateUrl: './form-asegurado.component.html',
})
export class FormAseguradoComponent {
  constructor(
    private fb: FormBuilder,
  ) {}

  form: FormGroup = this._buildForm();

  @Output()
  filledEvent = new EventEmitter<FormAsegurado>();

  isFormLoading = false;

  private _buildForm() {
    return this.fb.group({
      nombre: [null, Validators.required],
      apellido: [null, Validators.required],
      email: [null, [Validators.email, Validators.required]]
    });
  }

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
