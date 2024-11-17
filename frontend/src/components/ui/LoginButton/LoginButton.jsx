import React from 'react';
import './LoginButton.scss';

export const LoginButton = ({title}) => {
    return (
        <button className="button-login">
            {title}
        </button>
    );
};