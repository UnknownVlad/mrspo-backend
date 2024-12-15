import React, {useEffect, useState} from 'react';
import './UserPage.scss';
import {BookContent} from "../../components/BookContent/BookContent";
import {Menu} from "../../components/ui/Menu/Menu";


export const UserPage = () => {
    const userData = JSON.parse(localStorage.getItem('userData'));
    const [userBookData, setUserBookData] = useState(JSON.parse(localStorage.getItem('userBookData')));

    useEffect(() => {
        const handleStorageChange = () => {
            setUserBookData(JSON.parse(localStorage.getItem('userBookData')));
        };

        window.addEventListener('storage', handleStorageChange);

        return () => {
            window.removeEventListener('storage', handleStorageChange);
        };
    }, []);

    console.log(userBookData);

    return (
        <div className="user-page">
            <Menu user={userData}/>
            <BookContent bookInfo={userBookData.books}/>
        </div>
    );
};