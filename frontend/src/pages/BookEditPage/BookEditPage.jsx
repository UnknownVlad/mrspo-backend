import React, {useEffect, useState} from 'react';
import {Menu} from "../../components/ui/Menu/Menu";
import {BookEditContent} from "../../components/BookEditContent/BookEditContent";
import {addBookService, getBookById, updateBookService} from "../../utils/authService";
import {useLocation, useNavigate} from "react-router-dom";

export const BookEditPage = () => {
    const location = useLocation();
    const { bookId, isEdit } = location.state || {}; // Получаем только ID книги

    const initialFormData = {
        bookName: "",
        description: "",
        pageCount: "",
        circulation: "",
        authors: [],
        genres: [],
        rating: "",
        onSale: false,
    };

    const [formData, setFormData] = useState(initialFormData);

    useEffect(() => {
        if (isEdit && bookId) {
            getBookById(bookId)
                .then((data) => {
                    setFormData(data);
                })
        } else {
            setFormData(initialFormData);
        }
    }, [bookId, isEdit]);

    useEffect(() => {
        localStorage.setItem('bookFormData', JSON.stringify(formData));
    }, [formData]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        if (type === "checkbox") {
            setFormData((prev) => ({ ...prev, [name]: checked }));
        } else {
            setFormData((prev) => ({ ...prev, [name]: value }));
        }
    };

    const handleArrayChange = (name, value) => {
        setFormData((prev) => ({
            ...prev,
            [name]: value.split(",").map((item) => item.trim()),
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formattedData = {
            ...formData,
            pageCount: parseInt(formData.pageCount, 10),
            circulation: parseInt(formData.circulation, 10),
            rating: parseFloat(formData.rating),
        };

        try {
            if (isEdit) {
                const response = await updateBookService(bookId, formattedData);
                console.log("Book updated successfully:", response);
            } else {
                const response = await addBookService(formattedData);
                console.log("Book added successfully:", response);
            }

            setFormData({
                bookName: "",
                description: "",
                pageCount: "",
                circulation: "",
                authors: [],
                genres: [],
                rating: "",
                onSale: false,
            });
            localStorage.removeItem('bookFormData');
        } catch (error) {
            console.error("Error processing book:", error);
        }
    };

    return (
        <div className="user-page">
            <Menu user={JSON.parse(localStorage.getItem('userData'))} />
            <BookEditContent
                formData={formData}
                handleSubmit={handleSubmit}
                handleChange={handleChange}
                handleArrayChange={handleArrayChange}
                bookId={bookId}
            />
        </div>
    );
};