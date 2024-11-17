import React from 'react';
import './Input.scss';

export const Input = ({ span, placeholder, type }) => {
    return (
        <div className="login-input-block">
            <span>{span}</span>
            <input placeholder={placeholder} type={type}></input>
        </div>
    );
};