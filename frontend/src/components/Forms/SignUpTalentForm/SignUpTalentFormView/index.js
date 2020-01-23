import React from 'react';
import {Field} from 'formik';
import '../styles.scss';
import FKAuthTextInput from '../../../Fields/FKAuthTextInput/FKAuthTextInput';
import FKCheckbox from '../../../Fields/FKCheckbox/FKCheckbox';
import FKAuthDropdown from '../../../Fields/FKAuthDropdown/FKAuthDropdown';

const SignUpTalentFormView = ({
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
            containerClassName='fieldTalent'
        />
        <Field
            component={FKAuthTextInput}
            disabled={isSubmitting}
            name="email"
            placeholder='E-mail'
            containerClassName='fieldTalent'
        />
        <Field
            component={FKAuthDropdown}
            disabled={isSubmitting}
            name="location"
            placeholder='Location'
            containerClassName='fieldTalent'
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
      <div className='buttonsContainerTalent'>
        <button className='actionButton'
                disabled={!isValid || isSubmitting}
                onClick={handleSubmit}>
          get started
        </button>
      </div>

    </div>
);

export default SignUpTalentFormView;
