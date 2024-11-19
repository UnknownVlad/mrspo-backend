import React, { useState } from 'react';
import './Login.scss';
import { useLocation, NavLink } from 'react-router-dom';
import axios from 'axios';
import {LOGIN_ROUTE, REGISTRATION_ROUTE} from "../../utils/consts";
import {Input} from "../../components/ui/Input/Input";
import {LoginButton} from "../../components/ui/LoginButton/LoginButton";

const Login = () => {
    const location = useLocation();
    const isLogin = location.pathname === LOGIN_ROUTE;
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleRegister = async () => {
        console.log("Data sent:", {
            username: formData.username,
            password: formData.password
        });
        try {
            const response = await axios.post('http://localhost:8080/api/user/registration', {
                username: formData.username,
                password: formData.password
            });
            console.log('Registration successful:', response.data);
        } catch (error) {
            console.error('Registration failed:', error);
        }
    };

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/user/auth', {
                username: formData.username,
                password: formData.password
            });
            console.log('Login successful:', response.data);
        } catch (error) {
            console.error('Login failed:', error);
        }
    };

    const inputFields = [
        { span: 'Login', placeholder: 'Email or phone number', type: 'text', name: 'username' },
        { span: 'Password', placeholder: 'Enter password', type: 'password', name: 'password' }
    ];

    return (
        <div className="body-container">
            <div className="login-container">
                {isLogin ? (<p>Nice to see you again</p>) : (<p>Create your Free Account</p>)}
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
                    <LoginButton
                        title={isLogin ? 'Sign In' : 'Create Account'}
                        onClick={isLogin ? handleLogin : handleRegister}
                    />
                </div>
                <hr className="login-line" />
                <div className="login-bottom">
                    {isLogin ? (
                        <>
                            <p>Don't have an account?</p>
                            <NavLink to={REGISTRATION_ROUTE}><span>Sign up now</span></NavLink>
                        </>
                    ) : (
                        <>
                            <p>Already have an account?</p>
                            <NavLink to={LOGIN_ROUTE}><span>Log in</span></NavLink>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Login;
