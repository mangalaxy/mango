import React from 'react';
import {Field} from 'formik';
import FKTextInput from '../../../Fields/FKTextInput/FKTextInput';
import '../styles.scss';

const AboutContactFormView = ({
                                handleSubmit,
                                handleReset,
                                isSubmitting,
                                isValid,
                              }) => (
    <div className='about_contactForm'>
      <div className="fieldContainer">
        <Field
            component={FKTextInput}
            disabled={isSubmitting}
            name="email"
            placeholder='email'
            containerClassName='field'
        />
        <Field
            component={FKTextInput}
            disabled={isSubmitting}
            name="password"
            placeholder='Password'
            containerClassName='field'
        />
      </div>

      <div className='buttonsContainer'>
        <button className='actionButton'
                disabled={!isValid || isSubmitting}
                onClick={handleSubmit}>
          Send
        </button>
        <button className='actionButtonTransparent'
                disabled={!isValid || isSubmitting}
        >
          reset
        </button>
      </div>

    </div>
);

export default AboutContactFormView;