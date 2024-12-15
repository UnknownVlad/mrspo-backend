import React from 'react';
import add_icon from "../../pages/icon/add.svg";
import './AddButton.scss';
import {useNavigate} from "react-router-dom";
import {BOOK_EDIT} from "../../utils/consts";

export const AddButton = () => {
    let navigate = useNavigate();

    return (
        <button className="add-book" onClick={() => navigate(BOOK_EDIT)}>
            <img src={add_icon} alt="add new book"/>
            <p>new book</p>
        </button>
    );
};