import {Formik} from 'formik';
import React from 'react';
import SignUpFormView from '../SignUpFormView';
import * as Yup from 'yup';

const contactSchema = Yup.object().shape({
  fullName: Yup.string().
      min(2, 'Minimum 2 symbols').
      max(30, 'Maximum 30 symbols').
      required('Required field'),
  email: Yup.string().email('Invalid email format').required('Required field'),
});

const SignUpEmployerForm = ({onSuccess, onError}) => (
    <Formik
        onSubmit={(
            {email, password, rememberMe},
            {setStatus, setSubmitting}) => {
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

        component={SignUpFormView}
        validationSchema={contactSchema}
        initialValues={{
          locations: [
            {name: 'New York', code: 'NY'},
            {name: 'Rome', code: 'RM'},
            {name: 'London', code: 'LDN'},
            {name: 'Istanbul', code: 'IST'},
            {name: 'Paris', code: 'PRS'},
          ],
          role: 'employer',
        }}
    />
);

export default SignUpEmployerForm;
