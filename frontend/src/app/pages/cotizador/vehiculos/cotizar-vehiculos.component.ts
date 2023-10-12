import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CotizadorVehiculosService } from './cotizador-vehiculos.service';

@Component({
  templateUrl: './cotizar-vehiculos.component.html',
})
export class CotizarVehiculosComponent {
  constructor(
    private fb: FormBuilder,
    private cotizadorService: CotizadorVehiculosService
  ) {}

  form: FormGroup = this._buildForm();
  marcas$ = this.cotizadorService.getMarcas();

  private _buildForm() {
    return this.fb.group({
      marca: [],
      anio: [],
      modelo: [],
      version: [],
    });
  }

  submitForm() {
    console.log('submit', this.form.value);
  }
}
