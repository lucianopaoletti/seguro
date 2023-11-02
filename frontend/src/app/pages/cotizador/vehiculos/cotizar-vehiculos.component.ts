import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      marca: [{ value: null, disabled: false }, Validators.required],
      modelo: [{ value: null, disabled: true }, Validators.required],
      version: [null, Validators.required],
      anio: [null, Validators.required],
    });
  }

  marcaChange(marcaId: number) {
    const modelo = this.form.controls['modelo'];
    modelo.reset();
    modelo.enable();

    this.isModelosLoading = true;
    this.modelos$ = this.cotizadorService
      .getModelos(marcaId)
      .pipe(finalize(() => (this.isModelosLoading = false)));
  }

  modeloChange(modeloId: number) {
    const version = this.form.controls['version'];
    version.reset();

    if (!modeloId) {
      version.disable();
      return;
    } else {
      version.enable();
    }

    this.isVersionesLoading = true;
    this.versiones$ = this.cotizadorService
      .getVersiones(modeloId)
      .pipe(finalize(() => (this.isVersionesLoading = false)));
  }

  versionChange(versionId: number) {
    const anio = this.form.controls['anio'];
    anio.reset();

    if (!versionId) {
      anio.disable();
      return;
    } else {
      anio.enable();
    }

    this.isAniosLoading = true;
    this.anios$ = this.cotizadorService
      .getAniosFabricacion(versionId)
      .pipe(finalize(() => (this.isAniosLoading = false)));
  }

  submitForm() {
    if (this.form.valid) {
      console.log('submit', this.form.value);
    } else {
      Object.values(this.form.controls).forEach((control) => {
        console.log(control);
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
