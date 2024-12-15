import React, {Fragment, useState} from 'react';
import './BookContent.scss';
import ReactCardFlip from "react-card-flip";
import {AddButton} from "../AddButton/AddButton";
import pen from '../../pages/icon/pen.svg';
import {useNavigate} from "react-router-dom";
import {BOOK_EDIT} from "../../utils/consts";

export const BookContent = ({ bookInfo }) => {
    const [flippedCards, setFlippedCards] = useState({});
    let navigate = useNavigate();

    const handleCardClick = (id) => {
        setFlippedCards((prevState) => ({
            ...prevState,
            [id]: !prevState[id],
        }));
    };

    const handleBuyNowClick = (event) => {
        event.stopPropagation();
    };

    return (
        <div className="user-main">
            <div className="book-content">
                <h1 className="user-main-h1">Newest</h1>
                <div className="book-container">
                    {bookInfo.map((item) => (
                        <Fragment key={item.id}>
                            <ReactCardFlip
                                isFlipped={!!flippedCards[item.id]}
                                flipDirection="vertical">
                                <div
                                    className="book-block"
                                    onClick={() => handleCardClick(item.id)}>
                                    <img src={pen} alt="edit book"
                                         className="edit-button"
                                         onClick={() => {navigate(BOOK_EDIT, { state: { bookId: item.id, isEdit: true }})}}/>
                                    <h2>ID: {item.id}</h2>
                                    <p>{item.bookName}</p>
                                    <span>{item.authors.join(' ')}</span>
                                </div>
                                <div
                                    className="book-block-back"
                                    onClick={() => handleCardClick(item.id)}>
                                    <div className="book-status">
                                        <h3>{item.genres.join(' ')}</h3>
                                        <div className="book-rating">
                                            <p>{item.rating}</p>/<span>5.0</span>
                                        </div>
                                    </div>
                                    <p>{item.description}</p>
                                    {item.onSale ?
                                        <button className="buy-button" onClick={handleBuyNowClick}>Buy Now</button>
                                        :
                                        <button className="buy-button" disabled={true}>Not On Sale</button>}
                                </div>
                            </ReactCardFlip>
                        </Fragment>
                    ))}
                </div>
            </div>
            <div className="book-content-bottom">
                <AddButton/>
            </div>
        </div>
    );
};

