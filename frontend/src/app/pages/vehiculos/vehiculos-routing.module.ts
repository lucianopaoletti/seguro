import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CotizadorVehiculosComponent } from './cotizador/cotizador-vehiculos.component';
import { HistorialCotizacionesComponent } from './historial-cotizaciones/historial-cotizaciones.component';

const routes: Routes = [
  { path: 'cotizador', component: CotizadorVehiculosComponent },
  { path: 'historial', component: HistorialCotizacionesComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VehiculosRoutingModule {}
