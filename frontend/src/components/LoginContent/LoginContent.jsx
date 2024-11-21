import React from 'react';
import './LoginContent.scss';
import {Input} from "../ui/Input/Input";
import {LoginButton} from "../ui/LoginButton/LoginButton";
import {NavLink, useNavigate} from "react-router-dom";
import {REGISTRATION_ROUTE} from "../../utils/consts";
import {loginService} from "../../utils/authService";

export const LoginContent = ({ inputFields, formData, handleInputChange }) => {
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            await loginService(formData, navigate);
            console.log('Login successful');
        } catch (error) {
            console.error('Login failed:', error);
        }
    };

    return (
        <div className="login-container">
            <p>Nice to see you again</p>
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
            <div style={{marginTop: '20px'}}>
                <LoginButton title={'Sign In'} onClick={handleLogin}/>
            </div>
            <hr className="login-line"/>
            <div className="login-bottom">
                <p>Don't have an account?</p>
                <NavLink to={REGISTRATION_ROUTE}>
                    <span>Sign up now</span>
                </NavLink>
            </div>
        </div>
    );
};
