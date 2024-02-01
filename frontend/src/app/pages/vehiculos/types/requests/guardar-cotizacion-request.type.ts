export interface GuardarCotizacionRequest {
  vehiculo: {
    marcaId: number;
    modeloId: number;
    versionId: number;
    anioId: number;
  };
  coberturas: {
    numero: number;
    beneficios: {
      id: number;
      tasa: number;
    }[];
    precio: number;
  }[];
  coberturaSeleccionada: number;
  asegurado: {
    nombre: string;
    apellido: string;
    email: string;
  };
}
