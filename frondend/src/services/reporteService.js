import api from './api';

export const descargarReporteVentas = async () => {
  try {
    const response = await api.get('/ventas/reporte/pdf', {
      responseType: 'blob',
    });

    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', 'reporte_ventas.pdf');
    document.body.appendChild(link);
    link.click();
  } catch (error) {
    console.error('Error al descargar el reporte:', error);
    throw error;
  }
};
