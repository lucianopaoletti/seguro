import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzStepsModule } from 'ng-zorro-antd/steps';
import { NzResultModule } from 'ng-zorro-antd/result';
import { IconsProviderModule } from 'src/app/icons-provider.module';

import { CotizadorRoutingModule } from './cotizador-routing.module';
import { CotizadorVehiculosService } from './vehiculos/cotizador-vehiculos.service';
import { CotizadorVehiculosComponent } from './vehiculos/cotizador-vehiculos.component';
import { FormVehiculoComponent } from './vehiculos/form-vehiculo/form-vehiculo.component';
import { TableCoberturasComponent } from './vehiculos/table-coberturas/table-coberturas.component';
import { FormAseguradoComponent } from './vehiculos/form-asegurado/form-asegurado.component';
import { ResumenComponent } from './vehiculos/resumen/resumen.component';

@NgModule({
  imports: [
    CotizadorRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    IconsProviderModule,
    NzFormModule,
    NzInputModule,
    NzSelectModule,
    NzButtonModule,
    NzTableModule,
    NzStepsModule,
    NzResultModule,
  ],
  declarations: [
    CotizadorVehiculosComponent,
    FormVehiculoComponent,
    TableCoberturasComponent,
    FormAseguradoComponent,
    ResumenComponent,
  ],
  providers: [CotizadorVehiculosService],
})
export class CotizadorModule {}
