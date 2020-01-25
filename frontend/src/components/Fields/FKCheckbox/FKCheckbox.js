import React from 'react';
import {Checkbox} from 'primereact/checkbox';
import '../fieldStyles.scss';

const FKCheckbox = ({
                      inputClassName,
                      containerClassName,
                      label,
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
    <div className={`${containerClassName} fkCheckbox`}>
      <div>
        <Checkbox
            onBlur={onBlur(name)}
            onChange={e => setFieldValue(name, e.checked)}
            checked={value}
            inputId={name}
        />
        <label htmlFor={name} className="p-checkbox-label`">{label}</label>
      </div>

    </div>
);

export default FKCheckbox;
