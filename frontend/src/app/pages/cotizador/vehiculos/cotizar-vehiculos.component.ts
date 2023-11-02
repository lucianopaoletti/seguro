import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, finalize, of } from 'rxjs';
import { CotizadorVehiculosService } from './cotizador-vehiculos.service';
import { Modelo } from './types/modelo.type';
import { Version } from './types/version.type';
import { AnioFabricacion } from './types/anio-fabricacion.type';

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

  isVersionesLoading = false;
  versiones$: Observable<Version[]> = of([]);

  isAniosLoading = false;
  anios$: Observable<AnioFabricacion[]> = of([]);

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

  modeloChange(modeloId: number) {
    this.form.controls['version'].reset();

    if (!modeloId) {
      return;
    }

    this.isVersionesLoading = true;
    this.versiones$ = this.cotizadorService
      .getVersiones(modeloId)
      .pipe(finalize(() => (this.isVersionesLoading = false)));
  }

  versionChange(versionId: number) {
    this.form.controls['anio'].reset();

    if (!versionId) {
      return;
    }

    this.isAniosLoading = true;
    this.anios$ = this.cotizadorService
      .getAniosFabricacion(versionId)
      .pipe(finalize(() => (this.isAniosLoading = false)));
  }

  submitForm() {
    console.log('submit', this.form.value);
  }
}
