<template>
  <div>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="#">Inventario & Ventas</a>
        <button 
          class="navbar-toggler" 
          type="button" 
          data-bs-toggle="collapse" 
          data-bs-target="#navbarNav"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/" class="nav-link">Inicio</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/productos" class="nav-link">Productos</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/ventas" class="nav-link">Ventas</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/reportes" class="nav-link">Reportes</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Aquí se renderizan las rutas -->
    <div class="container mt-4">
      <router-view />
    </div>
  </div>
</template>

<script setup>
</script>

<style>
/* Estilos opcionales */
body {
  font-family: Arial, sans-serif;
  background-color: #f8f9fa;
}
</style>
