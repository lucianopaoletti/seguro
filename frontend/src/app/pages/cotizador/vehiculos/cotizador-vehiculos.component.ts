import { Component } from '@angular/core';
import { forkJoin } from 'rxjs';

import { CotizadorVehiculosService } from './cotizador-vehiculos.service';
import { Cobertura } from './types/cobertura.type';
import { FormVehiculo } from './form-vehiculo/types/form-vehiculo.type';
import { Beneficio } from './types/beneficio.type';
import { FormAsegurado } from './form-asegurado/types/form-asegurado.type';

@Component({
  templateUrl: './cotizador-vehiculos.component.html',
  styleUrls: ['./cotizador-vehiculos.component.scss'],
})
export class CotizadorVehiculosComponent {
  constructor(private cotizadorService: CotizadorVehiculosService) {}

  stepIndex = 0;

  coberturasAndBeneficios!: {
    coberturas: Cobertura[];
    beneficios: Beneficio[];
  };

  cotizacion: {
    vehiculo?: FormVehiculo;
    cobertura?: Cobertura;
    asegurado?: FormAsegurado;
  } = {};

  cotizacionGuardadaId = 0;

  cotizarCoberturas(params: FormVehiculo) {
    this.cotizacion.vehiculo = params;

    forkJoin({
      coberturas: this.cotizadorService.cotizar(params),
      beneficios: this.cotizadorService.getBeneficios(),
    }).subscribe({
      next: (response) => {
        this.coberturasAndBeneficios = response;
        this.stepIndex++;
      },
    });
  }

  selectCobertura(cobertura: Cobertura) {
    this.cotizacion.cobertura = cobertura;

    this.stepIndex++;
  }

  guardarCotizacion(asegurado: FormAsegurado) {
    this.cotizacion.asegurado = asegurado;

    this.cotizadorService.guardarCotizacion({
      vehiculo: this.cotizacion.vehiculo!,
      cobertura: this.cotizacion.cobertura!,
      asegurado: this.cotizacion.asegurado!,
    }).subscribe({
      next: (response) => {
        this.cotizacionGuardadaId = response.id;
        
        this.stepIndex++;
      }
    });
  }
}
