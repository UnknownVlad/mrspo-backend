import React from 'react';
import './RegistrationContent.scss';
import {Input} from "../ui/Input/Input";
import {LoginButton} from "../ui/LoginButton/LoginButton";
import {NavLink} from "react-router-dom";
import {LOGIN_ROUTE} from "../../utils/consts";
import {registerService} from "../../utils/authService";

export const RegistrationContent = ({ inputFields, formData, handleInputChange }) => {

    const handleRegister = async () => {
        try {
            await registerService(formData);
            console.log('Registration successful');
        } catch (error) {
            console.error('Registration failed:', error);
        }
    };

    return (
        <div className="login-container">
            <p>Create your Free Account</p>
            {inputFields.map((field, index) => (
                <Input
                    key={index}
                    span={field.span}
                    placeholder={field.placeholder}
                    type={field.type}
                    name={field.name}
                    value={formData[field.name]}
                    onChange={handleInputChange}
                />
            ))}
            <div style={{ marginTop: '20px' }}>
                <LoginButton title={'Create Account'} onClick={handleRegister} />
            </div>
            <hr className="login-line" />
            <div className="login-bottom">
                <p>Already have an account?</p>
                <NavLink to={LOGIN_ROUTE}>
                    <span>Log in</span>
                </NavLink>
            </div>
        </div>
    );
};

