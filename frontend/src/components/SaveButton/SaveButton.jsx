import React from 'react';
import save from '../../pages/icon/save.svg';
import './SaveButton.scss';

export const SaveButton = () => {
    return (
        <button type="submit" className="save-button">
            <img src={save} alt="save"/>
            <p>Save</p>
        </button>
    );
};