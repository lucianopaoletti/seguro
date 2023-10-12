import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { CotizadorRoutingModule } from './cotizador-routing.module';
import { CotizarVehiculosComponent } from './vehiculos/cotizar-vehiculos.component';
import { CotizadorVehiculosService } from './vehiculos/cotizador-vehiculos.service';

@NgModule({
  imports: [
    CotizadorRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    NzFormModule,
    NzSelectModule,
    NzButtonModule,
  ],
  declarations: [CotizarVehiculosComponent],
  providers: [CotizadorVehiculosService],
})
export class CotizadorModule {}
