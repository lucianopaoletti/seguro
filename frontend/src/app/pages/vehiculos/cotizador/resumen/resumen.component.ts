import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-resumen',
  templateUrl: './resumen.component.html',
})
export class ResumenComponent {
  @Input()
  numeroCotizacion!: number;
}
