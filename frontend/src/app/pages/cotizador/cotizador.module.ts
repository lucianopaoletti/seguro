import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzStepsModule } from 'ng-zorro-antd/steps';
import { IconsProviderModule } from 'src/app/icons-provider.module';

import { CotizadorRoutingModule } from './cotizador-routing.module';
import { CotizadorVehiculosService } from './vehiculos/cotizador-vehiculos.service';
import { CotizadorVehiculosComponent } from './vehiculos/cotizador-vehiculos.component';
import { FormVehiculoComponent } from './vehiculos/form-vehiculo/form-vehiculo.component';
import { TableCoberturasComponent } from './vehiculos/table-coberturas/table-coberturas.component';

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
    NzStepsModule,
  ],
  declarations: [
    CotizadorVehiculosComponent,
    FormVehiculoComponent,
    TableCoberturasComponent,
  ],
  providers: [CotizadorVehiculosService],
})
export class CotizadorModule {}
