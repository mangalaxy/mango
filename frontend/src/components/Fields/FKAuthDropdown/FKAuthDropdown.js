import React from 'react';
import '../fieldStyles.scss';
import {Dropdown} from 'primereact/dropdown';

const FKAuthDropdown = ({
                          placeholder,
                          containerClassName,
                          options,
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
    <div className={`${containerClassName} fkAuthInput fkDropdown`}>
        <span className="input-label">
        <label htmlFor="in" className="dropdownLabel">{placeholder}</label>
        </span>
      <Dropdown
          onChange={e => setFieldValue(name, e.value)}
          onBlur={onBlur(name)}
          value={value}
          options={options}
          optionLabel="name"
          placeholder={'Select...'}
      />
      <div className="errorContainer">
        {errors[name] && touched[name] &&
        <span className='errorLabel'>{errors[name]}</span>}
      </div>

    </div>
);

export default FKAuthDropdown;
