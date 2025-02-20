import axios from 'axios';

const API_URL = 'http://localhost:8081/api/productos'; // Ajusta según tu backend

export const obtenerProductos = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error obteniendo productos:', error);
    return [];
  }
};

export const agregarProducto = async (producto) => {
  try {
    const response = await axios.post(API_URL, producto);
    return response.data;
  } catch (error) {
    console.error('Error agregando producto:', error);
    throw error;
  }
};

export const eliminarProducto = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error('Error eliminando producto:', error);
    throw error;
  }
};
