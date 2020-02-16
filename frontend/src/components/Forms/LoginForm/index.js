import React from 'react';
import {Formik} from 'formik';
import * as Yup from 'yup';
import LoginFormView from './LoginFormView';

const contactSchema = Yup.object().shape(
      {
        name: Yup.string()
              .min(2, 'Minimum 2 symbols')
              .max(30, 'Maximum 30 symbols')
              .required('Required field'),
        email: Yup.string()
              .email('Invalid email format')
              .required('Required field'),
        message: Yup.string()
              .required('Required field'),
      }
);

const LoginForm = ({onSuccess, onError}) => (
      <Formik onSubmit={(
            {email, password, rememberMe}, {resetForm, setStatus, setSubmitting}) => {
        setStatus({});
        try {
          let data = {};
          data.email = email;
          data.password = password;
          data.rememberMe = rememberMe;

          // TODO: do query to API
          onSuccess();
          // resetForm();
        } catch (err) {
          setStatus({failed: true});
          setSubmitting(false);
          onError();
        }

      }
      }

              component={LoginFormView}
              validationSchema={contactSchema}
              initialValues={{}}
      />
);

export default LoginForm;
