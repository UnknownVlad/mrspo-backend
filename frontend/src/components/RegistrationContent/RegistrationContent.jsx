import React, {useEffect, useState} from 'react';
import './RegistrationContent.scss';
import {Input} from "../ui/Input/Input";
import {LoginButton} from "../ui/LoginButton/LoginButton";
import {NavLink} from "react-router-dom";
import {LOGIN_ROUTE} from "../../utils/consts";
import {registerService} from "../../utils/authService";

export const RegistrationContent = ({ inputFields, formData, handleInputChange }) => {
    const [errorMessage, setErrorMessage] = useState(null);
    const [errorFields, setErrorFields] = useState({});

    const handleRegister = async () => {
        try {
            await registerService(formData);
            setErrorMessage(null);
            setErrorFields({});
        } catch (error) {
            setErrorMessage(error.message);

            const newErrorFields = {};
            inputFields.forEach(field => {
                newErrorFields[field.name] = true;
            });

            setErrorFields(newErrorFields);
        }
    };

    useEffect(() => {
        console.log(errorFields);
    }, [errorFields]);

    return (
        <div className="login-container">
            <p>Create your Free Account</p>
            {inputFields.map((field, index) => {
                const hasError = errorFields[field.name];

                return (
                    <div key={index}>
                        <Input
                            span={field.span}
                            placeholder={field.placeholder}
                            type={field.type}
                            name={field.name}
                            value={formData[field.name]}
                            onChange={handleInputChange}
                            hasError={hasError}
                        />
                    </div>
                );
            })}
            {errorMessage &&
                <p className="error-login">{errorMessage}</p>
            }
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

