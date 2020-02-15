import React from 'react';
import {Field} from 'formik';
import FKTextInput from '../../../Fields/FKTextInput/FKTextInput';
import '../styles.scss';
import FKTextAreaInput from '../../../Fields/FKTextAreaInput/FKTextAreaInput';

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
            name="name"
            placeholder='Name'
            containerClassName='field'
        />
        <Field
            component={FKTextInput}
            disabled={isSubmitting}
            name="email"
            placeholder='Email'
            containerClassName='field'
        />
      </div>
      <Field
          component={FKTextAreaInput}
          name='message'
          resize={true}
          placeholder='Message'
          containerClassName='field'
      />
      <div className='buttonsContainer'>
        <button className='actionButton' disabled={!isValid || isSubmitting} onClick={handleSubmit}>
          Send
        </button>
        <button className='actionButtonTransparent' disabled={!isValid || isSubmitting}>
          reset
        </button>
      </div>

    </div>
);

export default AboutContactFormView;