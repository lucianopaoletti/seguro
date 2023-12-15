import { Component } from '@angular/core';
import { forkJoin } from 'rxjs';

import { CotizadorVehiculosService } from './cotizador-vehiculos.service';
import { Cobertura } from './types/cobertura.type';
import { FormVehiculo } from './form-vehiculo/types/form-vehiculo.type';
import { Beneficio } from './types/beneficio.type';

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

  cotizarCoberturas(params: FormVehiculo) {
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
    this.stepIndex++;
  }

  previousStep() {
    this.stepIndex--;
  }
}
