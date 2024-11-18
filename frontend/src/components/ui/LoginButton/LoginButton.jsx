import React from 'react';
import './LoginButton.scss';

export const LoginButton = ({ title, onClick }) => {
    return (
        <button className="button-login" onClick={onClick}>
            {title}
        </button>
    );
};