import React from 'react';
import './Login.scss';
import {LoginButton} from "../../components/ui/LoginButton/LoginButton";
import {NavLink, useLocation} from "react-router-dom";
import {LOGIN_ROUTE, REGISTRATION_ROUTE} from "../../utils/consts";
import {Input} from "../../components/ui/Input/Input";


const Login = () => {
    const location = useLocation();
    const isLogin = location.pathname === LOGIN_ROUTE;

    const inputFields = [
        { span: 'Login', placeholder: 'Email or phone number', type: 'text' },
        { span: 'Password', placeholder: 'Enter password', type: 'password' }
    ];

    return (
        <div className="body-container">
            <div className="login-container">
                {isLogin ? (<><p>Nice to see you again</p></>) : <><p>Create your Free Account</p></>}
                {inputFields.map((field, index) => (
                    <Input
                        key={index}
                        span={field.span}
                        placeholder={field.placeholder}
                        type={field.type}
                    />
                ))}
                <div style={{marginTop: '20px'}}>
                    <LoginButton title={isLogin ? 'Sign In' : 'Create Account'}/>
                </div>
                <hr className="login-line"/>
                <div className="login-bottom">
                    {isLogin ? (
                        <>
                            <p>Don't have an account?</p><NavLink
                            to={REGISTRATION_ROUTE}><span>Sign up now</span></NavLink>
                        </>) : (
                        <>
                            <p>Already have a account?</p><NavLink
                            to={LOGIN_ROUTE}><span>Log in</span></NavLink>
                        </>)}
                </div>
            </div>
        </div>
    );
};

export default Login;