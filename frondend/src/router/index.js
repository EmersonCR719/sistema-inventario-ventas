import { createRouter, createWebHistory } from 'vue-router';

// Importar las vistas
import HomeView from '../views/HomeView.vue';
import ProductosView from '../views/ProductosView.vue';
import VentasView from '../views/VentasView.vue';
import ReportesView from '../views/ReportesView.vue';

const routes = [
  { path: '/', component: HomeView },
  { path: '/productos', component: ProductosView },
  { path: '/ventas', component: VentasView },
  { path: '/reportes', component: ReportesView }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
