import React from 'react';
import {InputText} from 'primereact/inputtext';
import '../fieldStyles.scss';

const TextInput = ({
                     placeholder,
                     defaultValue,
                     inputClassName,
                     containerClassName,
                     keyfilter,
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
    <div className={`${containerClassName} textInput`}>

        <InputText
            keyfilter={keyfilter}
            className={inputClassName}
            onChange={e => setFieldValue(name, e.target.value)}
            onBlur={onBlur(name)}
            defaultValue={defaultValue}
            value={value}
        />

      <div className="errorContainer">
        {errors[name] && touched[name] &&
        <span className='errorLabel'>{errors[name]}</span>}
      </div>

    </div>
);

export default TextInput;
