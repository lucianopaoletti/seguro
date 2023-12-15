import { Beneficio } from "./beneficio.type";

export interface Cobertura {
    numero: number;
    nombre: string;
    beneficios: Beneficio[];
    precio: number;
}