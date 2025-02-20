<template>
  <div>
    <h2>Lista de Ventas</h2>
    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Fecha</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="venta in ventas" :key="venta.id">
          <td>{{ venta.id }}</td>
          <td>{{ venta.fecha }}</td>
          <td>${{ venta.total }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import ventaService from '../services/ventaService';  // 👈 Importación correcta

export default {
  setup() {
    const ventas = ref([]);

    const obtenerVentas = async () => {
      try {
        ventas.value = await ventaService.obtenerVentas();
      } catch (error) {
        console.error('Error obteniendo ventas:', error);
      }
    };

    onMounted(() => {
      obtenerVentas();
    });

    return { ventas };
  }
};
</script>

<style>
.table {
  width: 100%;
  border-collapse: collapse;
}

.table th, .table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.table th {
  background-color: #f2f2f2;
  font-weight: bold;
}
</style>
