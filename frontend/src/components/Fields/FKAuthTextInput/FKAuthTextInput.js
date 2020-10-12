import React from 'react';
import '../fieldStyles.scss';

const FKAuthTextInput = ({
                           placeholder,
                           defaultValue,
                           inputClassName,
                           containerClassName,
                           secure,
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
        <span className="input-label">
        <label htmlFor="in">{placeholder}</label>
        </span>
      <input
          className={inputClassName}
          onChange={e => setFieldValue(name, e.target.value)}
          onBlur={onBlur(name)}
          defaultValue={defaultValue}
          value={value}
          type={secure ? 'password' : 'text'}
      />
      <div className="errorContainer">
        <span className='errorLabel'>{errors[name] && touched[name] ?
            errors[name] :
            ' '}</span>
      </div>
    </div>
);

export default FKAuthTextInput;
