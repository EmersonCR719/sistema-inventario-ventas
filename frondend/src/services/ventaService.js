import axios from 'axios';

const API_URL = 'http://localhost:8081/api/ventas';

const obtenerVentas = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error obteniendo ventas:', error);
        throw error;
    }
};

export default { obtenerVentas };
