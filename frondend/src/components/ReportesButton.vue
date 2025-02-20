<template>
    <button @click="descargarReporte">Descargar Reporte de Ventas</button>
  </template>
  
  <script setup>
  import { descargarReporteVentas } from '../services/reporteService';
  
  const descargarReporte = async () => {
    await descargarReporteVentas();
  };
  </script>
  