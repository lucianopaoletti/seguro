import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CotizadorVehiculosService } from './cotizador-vehiculos.service';
import { Observable, finalize, of } from 'rxjs';
import { Modelo } from './types/modelo.type';

@Component({
  templateUrl: './cotizar-vehiculos.component.html',
})
export class CotizarVehiculosComponent {
  constructor(
    private fb: FormBuilder,
    private cotizadorService: CotizadorVehiculosService
  ) {}

  form: FormGroup = this._buildForm();

  isMarcasLoading = true;
  marcas$ = this.cotizadorService
    .getMarcas()
    .pipe(finalize(() => (this.isMarcasLoading = false)));

  isModelosLoading = false;
  modelos$: Observable<Modelo[]> = of([]);

  private _buildForm() {
    return this.fb.group({
      marca: [],
      modelo: [],
      version: [],
      anio: [],
    });
  }

  marcaChange(marcaId: number) {
    this.form.controls['modelo'].reset();

    this.isModelosLoading = true;
    this.modelos$ = this.cotizadorService
      .getModelos(marcaId)
      .pipe(finalize(() => (this.isModelosLoading = false)));
  }

  submitForm() {
    console.log('submit', this.form.value);
  }
}
