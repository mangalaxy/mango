import {Formik} from 'formik';
import React from 'react';
import AboutContactFormView from './AboutContactFormView';
import * as Yup from 'yup';

const contactSchema = Yup.object().shape({
  name: Yup.string().
      min(2, 'Minimum 2 symbols').
      max(30, 'Maximum 30 symbols').
      required('Required field'),
  email: Yup.string().email('Invalid email format').required('Required field'),
  message: Yup.string().required('Required field'),
});

const AboutContactForm = ({onSuccess, onError}) => (
    <Formik
        onSubmit={(
            {name, email, message}, {resetForm, setStatus, setSubmitting}) => {
          setStatus({});
          try {
            let data = {};
            let submitError = false;
            data.email = email;
            data.name = name;
            data.message = message;

            // TODO: do query ti API
            onSuccess();
            // resetForm();
          } catch (err) {
            setStatus({failed: true});
            setSubmitting(false);
            onError();
          }

        }
        }

        component={AboutContactFormView}
        validationSchema={contactSchema}
        initialValues={{}}
    />
);

export default AboutContactForm;
