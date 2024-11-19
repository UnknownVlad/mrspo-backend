import axios from "axios";

const API_URL = 'http://localhost:8080/api/user';

export const loginService = async (formData) => {
    const { username, password } = formData;
    try {
        const response = await axios.post(`${API_URL}/auth`, { username, password });
        return response.data;
    } catch (error) {
        console.error('Login failed:', error);
        throw error;
    }
};

export const registerService = async (formData) => {
    const { username, password } = formData;
    try {
        const response = await axios.post(`${API_URL}/registration`, { username, password });
        return response.data;
    } catch (error) {
        console.error('Registration failed:', error);
        throw error;
    }
};