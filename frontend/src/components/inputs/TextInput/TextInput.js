import React, {Fragment} from 'react';
import {Field} from 'redux-form';
import './TextInput.scss';
import SvgIcon from '../../SvgIcon/SvgIcon'

function TextInput(props) {
    const {name, label, type, placeholder, defaultValue, withIcon, icon, halfWidth, className, onChange} = props;

    return (
        <Fragment>
            {label && <label className="control-label">{label}</label>}
            <div className={`text-input
            ${halfWidth && 'text-input--half-width'}
            ${withIcon && 'text-input--margin-bottom'}
            `}>
                <input
                    onChange={onChange}
                    name={name}
                    type={type}
                    placeholder={placeholder || ''}
                    type={type}
                    className={`text-input__field
                                ${withIcon && 'text-input__field--border-none'} ${className}`}
                    defaultValue={defaultValue || ''}/>
                {withIcon &&
                <div className='icon-container'><SvgIcon type={icon}/></div>
                }
            </div>
        </Fragment>
    )
}

export default TextInput;