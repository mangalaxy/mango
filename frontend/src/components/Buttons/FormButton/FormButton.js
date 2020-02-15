import React from 'react';
import './FormButton.scss';

function FormButton(props) {
    const {text, className, onClick, type} = props
    const click = (e) => {
        if (onClick) {
            e.preventDefault()
            onClick()
        }
    }

    return (
        <button type={type} className={`form-button ${className || ''}`} onClick={click}>{text}</button>
    )
}

export default FormButton;