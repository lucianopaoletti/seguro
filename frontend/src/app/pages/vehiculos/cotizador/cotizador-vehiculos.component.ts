import { Component } from '@angular/core';
import { Subject, forkJoin } from 'rxjs';

import { VehiculosService } from '../vehiculos.service';
import { Cobertura } from '../types/cobertura.type';
import { FormVehiculo } from './form-vehiculo/types/form-vehiculo.type';
import { Beneficio } from '../types/beneficio.type';
import { FormAsegurado } from './form-asegurado/types/form-asegurado.type';

@Component({
  templateUrl: './cotizador-vehiculos.component.html',
  styleUrls: ['./cotizador-vehiculos.component.scss'],
})
export class CotizadorVehiculosComponent {
  // --------------------------------------------------------------------------------
  // Constructor

  constructor(private vehiculosService: VehiculosService) {}

  // --------------------------------------------------------------------------------
  // Atributos

  stepIndex = 0;

  coberturas: Cobertura[] = [];
  beneficios: Beneficio[] = [];

  cotizacion: {
    vehiculo?: FormVehiculo;
    cobertura?: Cobertura;
    asegurado?: FormAsegurado;
  } = {};

  cotizacionGuardadaId = 0;

  formError$: Subject<string> = new Subject<string>();

  // --------------------------------------------------------------------------------
  // Metodos

  cotizarCoberturas(params: FormVehiculo) {
    this.cotizacion.vehiculo = params;

    forkJoin({
      coberturas: this.vehiculosService.cotizar({
        marcaId: params.marca,
        modeloId: params.modelo,
        versionId: params.version,
        anioId: params.anio,
      }),
      beneficios: this.vehiculosService.getBeneficios(),
    }).subscribe({
      next: (response) => {
        this.coberturas = response.coberturas;
        this.beneficios = response.beneficios;
        this.stepIndex++;
      },
    });
  }

  selectCobertura(cobertura: Cobertura) {
    this.cotizacion.cobertura = cobertura;

    this.stepIndex++;
  }

  selectAsegurado(asegurado: FormAsegurado) {
    this.cotizacion.asegurado = asegurado;
    this.guardarCotizacion();
  }

  guardarCotizacion() {
    this.vehiculosService
      .guardarCotizacion({
        vehiculo: {
          marcaId: this.cotizacion.vehiculo?.marca!,
          modeloId: this.cotizacion.vehiculo?.modelo!,
          versionId: this.cotizacion.vehiculo?.version!,
          anioId: this.cotizacion.vehiculo?.anio!,
        },
        coberturas: this.coberturas,
        asegurado: this.cotizacion.asegurado!,
        coberturaSeleccionada: this.cotizacion.cobertura?.numero!,
      })
      .subscribe({
        next: (response) => {
          this.cotizacionGuardadaId = response.id;

          this.stepIndex++;
        },
        error: () => {
          this.formError$.next("Se produjo un error al guardar la cotización");
        }
      });
  }
}
