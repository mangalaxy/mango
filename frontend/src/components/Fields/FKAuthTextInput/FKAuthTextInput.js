import React from 'react';
import {InputText} from 'primereact/inputtext';
import '../fieldStyles.scss';

const FKAuthTextInput = ({
                       placeholder,
                       defaultValue,
                       inputClassName,
                       containerClassName,
                       field: {
                         name,
                         onBlur,
                         value,
                       },
                       form: {
                         errors,
                         touched,
                         setFieldValue,
                       },
                     }) => (
    <div className={`${containerClassName} fkAuthInput`}>
        <span className="p-float-label">
        <InputText
            className={inputClassName}
            onChange={e => setFieldValue(name, e.target.value)}
            onBlur={onBlur(name)}
            defaultValue={defaultValue}
            value={value}
        />
        <label htmlFor="in">{placeholder}</label>
        </span>
      <div className="errorContainer">
        {errors[name] && touched[name] &&
        <span className='errorLabel'>{errors[name]}</span>}
      </div>

    </div>
);

export default FKAuthTextInput;
