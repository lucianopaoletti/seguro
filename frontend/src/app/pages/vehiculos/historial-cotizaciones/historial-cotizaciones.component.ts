import { Component } from '@angular/core';
import { VehiculosService } from '../vehiculos.service';
import { map } from 'rxjs';

@Component({
  templateUrl: './historial-cotizaciones.component.html',
  styleUrls: ['./historial-cotizaciones.component.scss'],
})
export class HistorialCotizacionesComponent {
  constructor(private vehiculosService: VehiculosService) {}

  cotizaciones$ = this.vehiculosService
    .getCotizaciones()
    .pipe(map((page) => page.content));
}
