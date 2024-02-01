export interface Cotizacion {
  id: number;
  asegurado: {
    nombre: string;
    apellido: string;
    email: string;
  };
  vehiculo: {
    marca: {
      id: number;
      nombre: string;
    };
    modelo: {
      id: number;
      nombre: string;
    };
    version: {
      id: number;
      nombre: string;
    };
    anio: {
      id: number;
      anio: number;
    };
  };
  coberturaSeleccionada: number;
  fechaGuardado: string;
  usuario: {
    id: number;
    username: string;
    nombre: string;
  };
}
