import React, {useState} from 'react';
import './CheckBox.scss'

function CheckBox(props) {
    const {label, name, onChange} = props;
    const [checked, toggleChecked] = useState(false);
    const check = (e) => {
        toggleChecked(e.target.checked);
        if (onChange) {
            onChange(e.target.checked);
        }
    }

    return (
        <label className={`check-box ${checked && 'check-box--selected'}`}>
            {label && <div className='check-box__label'>{label}</div>}
            <input
                type='checkbox'
                hidden={true}
                name={name}
                onChange={check}
            />
        </label>
    )
}

export default CheckBox;