import React from 'react';
import './FormButton.scss';

function FormButton(props) {
    const {text, className, onClick} = props
    const click = (e) => {
        if (onClick) {
            e.preventDefault()
            onClick()
        }
    }

    return (
        <button className={`form-button ${className || ''}`} onClick={click}>{text}</button>
    )
}

export default FormButton;