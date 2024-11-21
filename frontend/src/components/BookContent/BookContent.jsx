import React, {useState} from 'react';
import './BookContent.scss';
import ReactCardFlip from "react-card-flip";

export const BookContent = ({ bookInfo }) => {
    const [isFlipped, setIsFlipped] = useState(false);

    const handleClick = () => {
        setIsFlipped(!isFlipped);
    };

    const handleMouseEnter = () => {
        setIsFlipped(true);
    };

    const handleMouseLeave = () => {
        setIsFlipped(false);
    };

    return (
        <div className="user-main">
            <h1 className="user-main-h1">Newest</h1>
            <div className="book-container">
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
            </div>
        </div>
    );
};
