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

import { VehiculosRoutingModule } from './vehiculos-routing.module';
import { VehiculosService } from './vehiculos.service';
import { CotizadorVehiculosComponent } from './cotizador/cotizador-vehiculos.component';
import { FormVehiculoComponent } from './cotizador/form-vehiculo/form-vehiculo.component';
import { TableCoberturasComponent } from './cotizador/table-coberturas/table-coberturas.component';
import { FormAseguradoComponent } from './cotizador/form-asegurado/form-asegurado.component';
import { ResumenComponent } from './cotizador/resumen/resumen.component';
import { HistorialCotizacionesComponent } from './historial-cotizaciones/historial-cotizaciones.component';

@NgModule({
  imports: [
    VehiculosRoutingModule,
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
    HistorialCotizacionesComponent,
  ],
  providers: [VehiculosService],
})
export class VehiculosModule {}
