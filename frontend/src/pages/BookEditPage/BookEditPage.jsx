import React from 'react';
import './BookEditPage.scss';
import {Menu} from "../../components/ui/Menu/Menu";

export const BookEditPage = () => {
    const userData = JSON.parse(localStorage.getItem('userData'));

    return (
        <div className="">
            <Menu user={userData}/>
            <p>admin page</p>
        </div>
    );
};