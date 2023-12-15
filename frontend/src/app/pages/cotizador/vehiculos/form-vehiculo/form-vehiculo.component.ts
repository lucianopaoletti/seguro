import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, finalize, of } from 'rxjs';

import { CotizadorVehiculosService } from '../cotizador-vehiculos.service';
import { Modelo } from '../types/modelo.type';
import { Version } from '../types/version.type';
import { AnioFabricacion } from '../types/anio-fabricacion.type';
import { FormVehiculo } from './types/form-vehiculo.type';

@Component({
  selector: 'app-form-vehiculo',
  templateUrl: './form-vehiculo.component.html',
})
export class FormVehiculoComponent {
  constructor(
    private fb: FormBuilder,
    private cotizadorService: CotizadorVehiculosService
  ) {}

  @Output()
  filledEvent = new EventEmitter<FormVehiculo>();

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
      version: [{ value: null, disabled: true }, Validators.required],
      anio: [{ value: null, disabled: true }, Validators.required],
    });
  }

  marcaChange(marcaId: number) {
    const modelo = this.form.controls['modelo'];
    modelo.reset();

    if (!marcaId) {
      modelo.disable();
      return;
    } else {
      modelo.enable();
    }

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
    if (!this.form.valid) {
      Object.values(this.form.controls).forEach((control) => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });

      return;
    }

    this.filledEvent.emit(this.form.value);
  }
}
