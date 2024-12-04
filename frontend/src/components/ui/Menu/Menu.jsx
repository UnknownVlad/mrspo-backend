import React from 'react';
import './Menu.scss';
import base_avatar from "../../../pages/icon/avatar.svg";
import exit from "../../../pages/icon/exit.svg";
import axios from "axios";
import {NavLink, useNavigate} from "react-router-dom";
import {BOOK_EDIT, USER_ROUTE} from "../../../utils/consts";
import book_icon from '../../../pages/icon/book.svg';
import edit_icon from '../../../pages/icon/edit.svg';


export const Menu = ({user}) => {
    const navigate = useNavigate();

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('userData');
        localStorage.removeItem('userBookData');

        delete axios.defaults.headers.common['Authorization'];
        navigate('/login');
    };

    return (
        <div className="user-container">
            <div className="user-top">
                <img src={base_avatar} alt="avatar" className="user-avatar"/>
                <h2 className="user-h2">{user.username}</h2>
                <p className="user-p">ID: {user.id}</p>
                <p className="user-p">Roles: {user.roles}</p>
                <p className="user-p">Welcome!</p>
            </div>
            <div className="navbar">
                <div className="navbar-block">
                    <img src={book_icon} alt="main"/><NavLink to={USER_ROUTE}><p>Main</p></NavLink>
                </div>
                <div className="navbar-block">
                    <img src={edit_icon} alt="edit"/><NavLink to={BOOK_EDIT}><p>Book Edit</p></NavLink>
                </div>
            </div>
            <div className="user-bottom" onClick={logout}>
                <img src={exit} alt="exit"/>
                <span className="user-bottom-span">Exit</span>
            </div>
        </div>
    );
};