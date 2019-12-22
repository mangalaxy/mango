import React, {Fragment} from 'react';
import {Field} from 'redux-form';
import './TextInput.scss';
import SvgIcon from '../../SvgIcon/SvgIcon'

function TextInput(props) {
    const {name, label, type, input, placeholder, defaultValue, withIcon, icon, halfWidth, className} = props;

    return (
        <Field
            name={name}
            component={() => {
                return (
                    <div>
                        {label && <label className="control-label">{label}</label>}
                        <div className={`text-input ${halfWidth && 'text-input--half-width'}`}>
                            <input
                                {...input}
                                placeholder={placeholder || ''}
                                type={type}
                                className={`text-input__field
                                ${withIcon && 'text-input__field--border-none'} ${className}`}
                                defaultValue={defaultValue || ''}/>
                            {withIcon &&
                            <div className='icon-container'><SvgIcon type={icon}/></div>
                            }
                        </div>
                    </div>
                    )
            }}
        />
    )
}

export default TextInput;