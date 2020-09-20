import React from 'react';
import {Field} from 'formik';
import '../styles.scss';
import FKAuthTextInput from '../../../Fields/FKAuthTextInput/FKAuthTextInput';
import FKCheckbox from '../../../Fields/FKCheckbox/FKCheckbox';

const LoginFormView = ({
                                handleSubmit,
                                handleReset,
                                isSubmitting,
                                isValid,
                              }) => (
    <div className='loginForm'>
      <div className="fieldContainer">
        <Field
            component={FKAuthTextInput}
            disabled={isSubmitting}
            name="email"
            placeholder='Email'
            containerClassName='field'
        />
        <Field
            component={FKAuthTextInput}
            disabled={isSubmitting}
            name="password"
            placeholder='Password'
            containerClassName='field'
            secure
        />
      </div>
      <div className='buttonsContainer'>
        <div>
          <Field
          component={FKCheckbox}
          disabled={isSubmitting}
          name='remember'
          label='Remember me'
          />
        </div>
        <span className='link'>Forgot password?</span>
      </div>
      <div className='buttonsContainer'>
        <button className='accent-btn'
                disabled={!isValid || isSubmitting}
                onClick={handleSubmit}>
          Login
        </button>
      </div>


    </div>
);

export default LoginFormView;
