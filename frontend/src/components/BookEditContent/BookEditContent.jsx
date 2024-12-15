import React from 'react';
import './BookEditContent.scss';
import {DeleteButton} from "../DeleteButton/DeleteButton";
import {SaveButton} from "../SaveButton/SaveButton";

export const BookEditContent = ({formData, handleSubmit, handleChange, handleArrayChange, bookId}) => {
    return (
        <div className="user-main">
            <form onSubmit={handleSubmit} className="edit-back">
                <div className="edit-container">
                    <div className="edit-content">
                        <div className="block-input">
                            <input
                                type="text"
                                name="bookName"
                                placeholder=""
                                value={formData.bookName}
                                onChange={handleChange}
                            />
                            <label>Book Name</label>
                        </div>
                        <div className="block-input">
                    <textarea
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                        placeholder=""
                    />
                            <label>Description</label>
                        </div>
                        <div className="block-input">
                            <input
                                type="text"
                                name="authors"
                                value={formData.authors.join(", ")}
                                onChange={(e) => handleArrayChange("authors", e.target.value)}
                                placeholder=""
                            />
                            <label>Authors (separate with commas)</label>
                        </div>
                        <div className="block-input">
                            <input
                                type="text"
                                name="genres"
                                value={formData.genres.join(", ")}
                                onChange={(e) => handleArrayChange("genres", e.target.value)}
                                placeholder=""
                            />
                            <label>Genres (separate with commas)</label>
                        </div>
                    </div>
                    <div className="edit-content">
                        <div className="block-input">
                            <input
                                type="number"
                                name="pageCount"
                                value={formData.pageCount}
                                onChange={handleChange}
                                placeholder=""
                            />
                            <label>Page Count</label>
                        </div>
                        <div className="block-input">
                            <input
                                type="number"
                                name="circulation"
                                value={formData.circulation}
                                onChange={handleChange}
                                placeholder=""
                            />
                            <label>Circulation</label>
                        </div>
                        <div className="block-input">
                            <input
                                type="number"
                                name="rating"
                                value={formData.rating}
                                onChange={handleChange}
                                placeholder=""
                            />
                            <label>Rating</label>
                        </div>
                        <div className="edit-checkbox">
                            <input
                                type="checkbox"
                                id="onSale"
                                name="onSale"
                                checked={formData.onSale}
                                onChange={handleChange}
                            />
                            <label htmlFor="onSale">On Sale</label>
                        </div>
                    </div>
                </div>

                <div className="edit-buttons">
                    {bookId && (
                        <DeleteButton id={bookId}/>
                    )}
                    <SaveButton />
                </div>
            </form>
        </div>
    );
};