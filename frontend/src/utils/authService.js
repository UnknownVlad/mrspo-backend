import axios from "axios";

const API_URL = 'http://localhost:8080/api/user';


export const getUserData = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/user/page');
        console.log('User data:', response.data);
        return response.data;
    } catch (error) {
        console.error('Failed to fetch user data:', error);
        throw error;
    }
};

export const getUserBookData = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/book/get/all');
        console.log('Book:', response.data);
        return response.data;
    } catch (error) {
        console.error('Failed to fetch book data:', error);
        throw error;
    }
};

export const loginService = async (formData, navigate) => {
    const { username, password } = formData;

    try {
        const response = await axios.post(`${API_URL}/auth`, { username, password });
        const token = response.data.token;

        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

        const userData = await getUserData();
        const userBookData = await getUserBookData();

        localStorage.setItem('userData', JSON.stringify(userData));
        localStorage.setItem('userBookData', JSON.stringify(userBookData));

        navigate('/account');

        return userData;
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