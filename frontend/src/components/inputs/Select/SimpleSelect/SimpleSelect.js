import React, {useState, Fragment} from 'react';
import './SimpleSelect.scss';
import {Field} from 'redux-form';
import Select from "react-dropdown-select";

function SimpleSelect(props) {
    const {input, name, onChange, options, values, className, multi, placeholder, label, directionTable} = props
    
    return (
        <div className={`simple-select ${directionTable && 'simple-select--direction-table'}`}>
            {label && <label className="control-label">{label}</label>}
            <Select
                name={name}
                options={options}
                onChange={onChange}
                className={`${className}`}
                multi={multi}
                placeholder={''}
                dropdownHandle={false}
                keepOpen={true}
                dropdownPosition="auto"
            />
        </div>
    )
}

export default SimpleSelect;