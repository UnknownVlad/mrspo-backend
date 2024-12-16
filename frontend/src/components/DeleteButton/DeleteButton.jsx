import React from 'react';
import delete_icon from "../../pages/icon/delete.svg";
import './DeleteButton.scss';
import {deleteBookById} from "../../utils/authService";

export const DeleteButton = ({id}) => {
    const handleDelete = async (id) => {
        if (window.confirm("Are you sure you want to delete this book?")) {
            try {
                await deleteBookById(id);
            } catch (error) {
                alert("Failed to delete the book. Please try again.");
            }
        }
    };

    return (
        <button className="delete-button" type="button" onClick={() => handleDelete(id)}>
            <img src={delete_icon} alt="delete"/>
            <p>Delete</p>
        </button>
    );
};
