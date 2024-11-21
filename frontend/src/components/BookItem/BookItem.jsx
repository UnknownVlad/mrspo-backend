import React from 'react';
import ReactCardFlip from "react-card-flip";
import './BookItem.scss';

export const BookItem = () => {


    return (
        <ReactCardFlip
            isFlipped={isFlipped}
            flipDirection="vertical">
            <div className="book-block"
                 onMouseEnter={handleMouseEnter}
                 onMouseLeave={handleMouseLeave}>
                <h2>ID: 261</h2>
                <p>The Good Guy</p>
                <span>Mark Mcallister</span>
            </div>
            <div className="book-block"
                 onMouseEnter={handleMouseEnter}
                 onMouseLeave={handleMouseLeave}>
                <p>Thriller Novel</p>
                <p>Interesting Book</p>
                <span>4.2</span>
                <span>On Sale</span>
            </div>
        </ReactCardFlip>
    );
};
