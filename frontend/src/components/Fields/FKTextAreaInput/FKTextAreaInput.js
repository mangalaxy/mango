import React from 'react';
import {InputTextarea} from 'primereact/inputtextarea';
import '../fieldStyles.scss';

const FKTextAreaInput = ({
                           placeholder,
                           defaultValue,
                           inputClassName,
                           containerClassName,
                           resize,
                           rows,
                           cols,
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
    <div className={`${containerClassName} fkInput`}>
        <InputTextarea
            rows={rows || 5}
            cols={cols || 30}
            autoResize={resize}
            className={inputClassName}
            onChange={e => setFieldValue(name, e.target.value)}
            onBlur={onBlur(name)}
            defaultValue={defaultValue}
            value={value}
            placeholder={placeholder}
        />
      <div className="errorContainer">
        {errors[name] && touched[name] &&
        <span className='errorLabel'>{errors[name]}</span>}
      </div>

    </div>
);

export default FKTextAreaInput;
