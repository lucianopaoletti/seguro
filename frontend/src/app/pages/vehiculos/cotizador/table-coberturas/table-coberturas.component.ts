import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Cobertura } from '../../types/cobertura.type';
import { Beneficio } from '../../types/beneficio.type';

@Component({
  selector: 'app-table-coberturas',
  templateUrl: './table-coberturas.component.html',
  styleUrls: ['./table-coberturas.component.scss'],
})
export class TableCoberturasComponent {
  @Input()
  coberturas!: Cobertura[];

  @Input()
  beneficios!: Beneficio[];

  @Output()
  selectCoberturaEvent = new EventEmitter<Cobertura>();

  coberturaPoseeBeneficio(cobertura: Cobertura, beneficio: Beneficio) {
    return cobertura.beneficios.map((b) => b.id).includes(beneficio.id);
  }

  selectCobertura(cobertura: Cobertura) {
    this.selectCoberturaEvent.emit(cobertura);
  }
}
