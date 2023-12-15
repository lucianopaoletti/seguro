import { Component } from '@angular/core';
import { Observable, forkJoin, of } from 'rxjs';
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

  coberturasAndBeneficios$: Observable<{
    coberturas: Cobertura[];
    beneficios: Beneficio[];
  }> = of();

  cotizarCoberturas(params: FormVehiculo) {
    this.coberturasAndBeneficios$ = forkJoin({
      coberturas: this.cotizadorService.cotizar(params),
      beneficios: this.cotizadorService.getBeneficios(),
    });
  }
}
