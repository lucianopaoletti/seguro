import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CotizarVehiculosComponent } from './vehiculos/cotizar-vehiculos.component';

const routes: Routes = [
  { path: 'vehiculos', component: CotizarVehiculosComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CotizadorRoutingModule {}
