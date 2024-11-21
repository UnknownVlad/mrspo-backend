import React from 'react';
import base_avatar from '../icon/avatar.svg';
import exit from '../icon/exit.svg';
import './UserPage.scss';
import {useLocation, useNavigate} from "react-router-dom";
import axios from "axios";
import {BookContent} from "../../components/BookContent/BookContent";


export const UserPage = () => {
    const location = useLocation();
    const navigate = useNavigate();

    const { userData, userBookData } = location.state || {};

    const logout = () => {
        localStorage.removeItem('token');
        delete axios.defaults.headers.common['Authorization'];
        navigate('/login');
    };

    return (
        <div className="user-page">
            <div className="user-container">
                <div className="user-top">
                    <img src={base_avatar} alt="avatar" className="user-avatar"/>
                    <h2 className="user-h2">{userData.username}</h2>
                    <p className="user-p">ID: {userData.id}</p>
                    <p className="user-p">Roles: {userData.roles}</p>
                    <p className="user-p">Welcome!</p>
                </div>
                <div className="user-bottom">
                    <img src={exit} alt="exit" className="user-bottom-img" onClick={logout}/>
                    <span className="user-bottom-span">Exit</span>
                </div>
            </div>
            <BookContent bookInfo={userBookData}/>
        </div>
    );
};