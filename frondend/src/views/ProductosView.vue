<template>
  <div class="container mt-4">
    <h1>Gestión de Productos</h1>

    <!-- Formulario para agregar producto -->
    <div class="mb-3">
      <input v-model="nuevoProducto.nombre" class="form-control mb-2" placeholder="Nombre del producto" />
      <input v-model="nuevoProducto.precio" type="number" class="form-control mb-2" placeholder="Precio" />
      <button @click="agregarNuevoProducto" class="btn btn-primary">Agregar Producto</button>
    </div>

    <!-- Tabla de productos -->
    <table class="table table-striped">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Precio</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="producto in productos" :key="producto.id">
          <td>{{ producto.id }}</td>
          <td>{{ producto.nombre }}</td>
          <td>{{ producto.precio }}</td>
          <td>
            <button @click="eliminar(producto.id)" class="btn btn-danger btn-sm">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { obtenerProductos, agregarProducto, eliminarProducto } from '../services/productoService';

export default {
  setup() {
    const productos = ref([]);
    const nuevoProducto = ref({ nombre: '', precio: '' });

    const cargarProductos = async () => {
      productos.value = await obtenerProductos();
      console.log('Productos obtenidos:', productos.value); 
    };

    const agregarNuevoProducto = async () => {
      if (!nuevoProducto.value.nombre || !nuevoProducto.value.precio) return;
      await agregarProducto(nuevoProducto.value);
      nuevoProducto.value = { nombre: '', precio: '' };
      cargarProductos();
    };

    const eliminar = async (id) => {
      await eliminarProducto(id);
      cargarProductos();
    };

    onMounted(cargarProductos);

    return {
      productos,
      nuevoProducto,
      agregarNuevoProducto,
      eliminar,
    };
  }
};
</script>
