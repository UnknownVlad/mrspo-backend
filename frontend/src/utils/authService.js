import axios from "axios";

const API_URL = 'http://localhost:8080/api';

export const getBookById = async (id) => {
    try {
        const token = localStorage.getItem("token");
        const response = await axios.get(`${API_URL}/book/get/${id}`, {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });
        return response.data.book;
    } catch (error) {
        console.error("Error fetching book:", error);
        throw error;
    }
};

export const updateBookService = async (id, bookData) => {
    const { id: _, ...dataWithoutId } = bookData;
    try {
        const token = localStorage.getItem("token");
        const response = await axios.put(
            `${API_URL}/book/update/${id}`,
            dataWithoutId,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            }
        );

        const updatedBook = response.data.book;
        const storedBooks = JSON.parse(localStorage.getItem('userBookData'))?.books || [];
        const updatedBooks = storedBooks.map((book) =>
            book.id === updatedBook.id ? updatedBook : book
        );
        localStorage.setItem('userBookData', JSON.stringify({ books: updatedBooks }));

        return response.data;
    } catch (error) {
        console.error("Error updating book:", error);
        throw error;
    }
};

export const deleteBookById = async (id) => {
    try {
        const token = localStorage.getItem("token");
        const response = await axios.delete(
            `${API_URL}/book/delete/${id}`,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            }
        );
        const updatedBooks = JSON.parse(localStorage.getItem('userBookData')) || { books: [] };
        updatedBooks.books = updatedBooks.books.filter(book => book.id !== id);
        localStorage.setItem('userBookData', JSON.stringify(updatedBooks));
        return response.data;
    } catch (error) {
        console.error("Error deleting book:", error);
        throw error;
    }
};

export const addBookService = async (bookData) => {
    try {
        const token = localStorage.getItem("token");
        const response = await axios.post(
            `${API_URL}/book/add`,
            bookData,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            }
        );

        const newBook = response.data.book;
        const updatedBooks = JSON.parse(localStorage.getItem('userBookData')) || { books: [] };

        updatedBooks.books.push(newBook);

        localStorage.setItem('userBookData', JSON.stringify(updatedBooks));

        return newBook;
    } catch (error) {
        throw error;
    }
};

export const getUserData = async () => {
    try {
        const response = await axios.get(`${API_URL}/user/page`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const getUserBookData = async () => {
    try {
        const response = await axios.get(`${API_URL}/book/get/all`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const loginService = async (formData, navigate) => {
    const { username, password } = formData;

    try {
        const response = await axios.post(`${API_URL}/user/auth`, { username, password });
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
        if (axios.isAxiosError(error)) {
            const errorMessage = error.response?.data.error.message || 'An unknown error occurred';
            throw new Error(errorMessage);
        } else {
            throw new Error('An unexpected error occurred');
        }
    }
};

export const registerService = async (formData) => {
    const { username, password } = formData;
    try {
        const response = await axios.post(`${API_URL}/user/registration`, { username, password });
        return response.data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            const errorMessage = error.response?.data.error.message || 'An unknown error occurred';
            throw new Error(errorMessage);
        } else {
            throw new Error('An unexpected error occurred');
        }
    }
};