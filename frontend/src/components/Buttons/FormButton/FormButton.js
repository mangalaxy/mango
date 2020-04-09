import React from 'react';
import './FormButton.scss';

function FormButton(props) {
    const {text, className, onClick, type} = props;

    const handleClick = (e) => {
        e.preventDefault();
        if (onClick) {
            onClick(e);
        }
    };
    return (
        <button className={`form-button ${className || ''}`} type={type} onClick={handleClick}>{text}</button>
    )
}

export default FormButton;