import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzTableModule } from 'ng-zorro-antd/table';
import { CotizadorRoutingModule } from './cotizador-routing.module';
import { CotizadorVehiculosComponent } from './vehiculos/cotizador-vehiculos.component';
import { CotizadorVehiculosService } from './vehiculos/cotizador-vehiculos.service';
import { IconsProviderModule } from 'src/app/icons-provider.module';

@NgModule({
  imports: [
    CotizadorRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    IconsProviderModule,
    NzFormModule,
    NzSelectModule,
    NzButtonModule,
    NzTableModule,
  ],
  declarations: [
    CotizadorVehiculosComponent,
  ],
  providers: [CotizadorVehiculosService],
})
export class CotizadorModule {}
