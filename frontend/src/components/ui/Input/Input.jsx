import React from 'react';
import './Input.scss';

export const Input = ({ inputKey, span, placeholder, type, name, value, onChange }) => {
    return (
        <div className="login-input-block" key={inputKey}>
            <span>{span}</span>
            <input
                placeholder={placeholder}
                type={type}
                name={name}
                value={value}
                onChange={onChange}/>
        </div>
    );
};