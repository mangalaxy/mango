import React, {useState, Fragment} from 'react';
import './DropDownSelect.scss';
import {Field} from 'redux-form';
import Select from "react-dropdown-select";

function DropDownSelect(props) {
    const {input, name, onChange, options, values, className, multi, placeholder, label, halfWidth} = props

    return (
        <div className={`dropdown-select ${halfWidth && 'dropdown-select--half-width'}`}>
            {label && <label className="control-label">{label}</label>}
            <Select
                name={name}
                options={options}
                onChange={onChange}
                className={className}
                multi={multi}
                placeholder={placeholder}
            />
        </div>
    )
}

export default DropDownSelect;