import React, {useState, Fragment} from 'react';
import './DropDownSelect.scss';
import Select from "react-dropdown-select";

function DropDownSelect(props) {
    const {name, onChange, options, values, className,
        multi, placeholder, label, halfWidth, searchable} = props

    const onSelect = (selected) => {
        if (onChange) {
            const values = selected.map(value => value.value);
            onChange(multi ? values : values[0]);
        }
    };
    return (
        <div className={`dropdown-select ${halfWidth && 'dropdown-select--half-width'} ${className}`}>
            {label && <label className="control-label">{label}</label>}
            <Select
                options={options}
                onChange={onSelect}
                multi={multi}
                placeholder={placeholder}
                searchable={searchable}
                name={name}
                values={values}
            />
        </div>
    )
}

export default DropDownSelect;