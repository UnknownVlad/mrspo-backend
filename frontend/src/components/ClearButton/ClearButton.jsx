import React from 'react';
import './ClearButton.scss';
import clear from '../../pages/icon/clear.svg';

export const ClearButton = ({handleReset}) => {
    return (
        <button type="button" className="button-clear" onClick={handleReset}>
            <img src={clear} alt="clear form"/>
            <p>Clear</p>
        </button>
    );
};