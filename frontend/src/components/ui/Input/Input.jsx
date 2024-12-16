import React from 'react';
import './Input.scss';

export const Input = ({ inputKey, span, placeholder, type, name, value, onChange, hasError }) => {
    const inputStyle = hasError
        ? {
            border: '2px solid #B9382D',
            background: 'rgb(255 234 232)',
        }
        : {
            border: 'none',
            background: '#F2F2F2'
        };

    return (
        <div className="login-input-block" key={inputKey}>
            <span>{span}</span>
            <input
                placeholder={placeholder}
                type={type}
                name={name}
                value={value}
                onChange={onChange}
                style={inputStyle}
            />
        </div>
    );
};