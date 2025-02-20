<template>
    <div class="container mt-4">
      <h2 class="text-primary">Lista de Productos</h2>
      <ul class="list-group">
        <li 
          v-for="producto in productos" 
          :key="producto.id" 
          class="list-group-item d-flex justify-content-between align-items-center"
        >
          <span>{{ producto.nombre }} - ${{ producto.precio }}</span>
          <button @click="eliminarProducto(producto.id)" class="btn btn-danger btn-sm">
            Eliminar
          </button>
        </li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { getProductos, deleteProducto } from '../services/productoService';
  
  const productos = ref([]);
  
  const cargarProductos = async () => {
    productos.value = await getProductos();
  };
  
  const eliminarProducto = async (id) => {
    await deleteProducto(id);
    cargarProductos();
  };
  
  onMounted(cargarProductos);
  </script>
  