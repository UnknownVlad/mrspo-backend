import React, { useState } from 'react';
import './Login.scss';
import { useLocation } from 'react-router-dom';
import { LOGIN_ROUTE } from "../../utils/consts";
import { LoginContent } from "../../components/LoginContent/LoginContent";
import { RegistrationContent } from "../../components/RegistrationContent/RegistrationContent";

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

    const inputFields = [
        { span: 'Login', placeholder: 'Email or phone number', type: 'text', name: 'username' },
        { span: 'Password', placeholder: 'Enter password', type: 'password', name: 'password' }
    ];

    return (
        <div className="body-container">
            {isLogin ?
                <LoginContent inputFields={inputFields} formData={formData} handleInputChange={handleInputChange} />
                :
                <RegistrationContent inputFields={inputFields} formData={formData} handleInputChange={handleInputChange}/>}
        </div>
    );
};

export default Login;
