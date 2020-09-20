import React from 'react';
import {Field} from 'formik';
import './styles.scss';
import FKAuthTextInput from '../../Fields/FKAuthTextInput/FKAuthTextInput';
import FKCheckbox from '../../Fields/FKCheckbox/FKCheckbox';
import FKAuthDropdown from '../../Fields/FKAuthDropdown/FKAuthDropdown';

const SignUpFormView = ({
                          handleSubmit,
                          isSubmitting,
                          isValid,
                          initialValues,
                        }) => (
    <div className='signUpTalentForm'>
      <div className="fieldContainer">
        <Field
            component={FKAuthTextInput}
            disabled={isSubmitting}
            name="fullName"
            placeholder='Full name'
            containerClassName='field'
        />
        <Field
            component={FKAuthTextInput}
            disabled={isSubmitting}
            name="email"
            placeholder={initialValues.role==='employer'?'Company e-mail':'E-mail'}
            containerClassName='field'
        />
        {initialValues.role === 'employer' &&
        <><Field
            component={FKAuthTextInput}
            disabled={isSubmitting}
            name="phone"
            placeholder='Phone number'
            containerClassName='field'
        />
          <Field
              component={FKAuthTextInput}
              disabled={isSubmitting}
              name="company"
              placeholder='Company name'
              containerClassName='field'
          />
          <Field
              component={FKAuthTextInput}
              disabled={isSubmitting}
              name="jobTitle"
              placeholder='Job title'
              containerClassName='field'
          /></>}
        <Field
            component={FKAuthDropdown}
            disabled={isSubmitting}
            name="location"
            placeholder='Location'
            containerClassName='field'
            options={initialValues.locations}
        />
      </div>
      <div className='buttonsContainer'>
        <div>
          <Field
              component={FKCheckbox}
              disabled={isSubmitting}
              name='agree'
              label='I agree to the'
          />

        </div>
        <span className={'link'}>Terms & Privacy</span>
      </div>
      <div className='buttonsContainer'>
        <button className='accent-btn'
                disabled={!isValid || isSubmitting}
                onClick={handleSubmit}>
          get started
        </button>
      </div>
    </div>
);

export default SignUpFormView;
