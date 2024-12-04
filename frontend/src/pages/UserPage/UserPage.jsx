import React from 'react';
import './UserPage.scss';
import {BookContent} from "../../components/BookContent/BookContent";
import {Menu} from "../../components/ui/Menu/Menu";


export const UserPage = () => {
    const userData = JSON.parse(localStorage.getItem('userData'));
    const userBookData = JSON.parse(localStorage.getItem('userBookData'));

    return (
        <div className="user-page">
            <Menu user={userData}/>
            <BookContent bookInfo={userBookData.books}/>
        </div>
    );
};