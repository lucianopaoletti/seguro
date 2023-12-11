import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CotizadorVehiculosComponent } from './vehiculos/cotizador-vehiculos.component';

const routes: Routes = [
  { path: 'vehiculos', component: CotizadorVehiculosComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CotizadorRoutingModule {}
