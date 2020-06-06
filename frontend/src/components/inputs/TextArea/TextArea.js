import React, {Fragment} from 'react';
import './TextArea.scss';

function TextArea(props) {
    const {name, label, type, input, placeholder, defaultValue, halfWidth, className, height, onChange} = props;

    return (
        <Fragment>
            {label && <label className="control-label">{label}</label>}
            <div className={`text-area ${halfWidth && 'text-area--half-width'}`}>
                <textarea
                    name={name}
                    {...input}
                    placeholder={placeholder || ''}
                    type={type}
                    className={`text-area__field ${className}`}
                    defaultValue={defaultValue || ''}
                    style={{ height: height }}
                    onChange={onChange}
                />
            </div>
        </Fragment>
    )
}

export default TextArea;