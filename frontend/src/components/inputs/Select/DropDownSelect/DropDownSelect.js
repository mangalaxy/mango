import React, {useState, Fragment} from 'react';
import './DropDownSelect.scss';
import {Field} from 'redux-form';
import Select from "react-dropdown-select";

function DropDownSelect(props) {
    const {input, name, onChange, options, values, className,
        multi, placeholder, label, halfWidth, searchable, contentRenderer} = props

    return (
        <div className={`dropdown-select
                        ${halfWidth && 'dropdown-select--half-width'}
                        ${className}
                        `}>
            {label && <label className="control-label">{label}</label>}
            <Field
                name={name}
                options={options}
                component={() => {
                    return <Select
                                options={options}
                                onChange={onChange}
                                multi={multi}
                                placeholder={placeholder}
                                searchable={searchable}
                                {...props}
                            />
                }}
            />
        </div>
    )
}

export default DropDownSelect;