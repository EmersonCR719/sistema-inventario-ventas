import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8081/api', // Ajusta la URL según tu backend
});

export default api;