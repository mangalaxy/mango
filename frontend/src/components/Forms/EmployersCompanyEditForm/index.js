import {Formik} from 'formik';
import React from 'react';
import * as Yup from 'yup';
import EmployersCompanyEditFormView from './EmployersCompanyEditFormView';

const companySchema = Yup.object().shape({
  name: Yup.string().
      min(2, 'Minimum 2 symbols').
      max(50, 'Maximum 30 symbols').
      required('Required field'),
});

const EmployersCompanyEditForm = ({companyProfile, onSuccess}) => (
    <Formik
        onSubmit={(
            values,
            // {logo, name,slogan, location,employersCountRange,category, images,
            //   aboutCompany,techStack,perks,benefits,links},
            {setStatus, setSubmitting}) => {
          setStatus({});
          try {
            let data = {values};

            // TODO: do query to API
            onSuccess(values);
          } catch (err) {
            setStatus({failed: true});
            setSubmitting(false);
          }

        }
        }

        component={EmployersCompanyEditFormView}
        validationSchema={companySchema}
        initialValues={{...companyProfile, employersRanges: employersRanges}}
    />
);

export default EmployersCompanyEditForm;

const employersRanges = [
  {label: '0-20', value: '0-20'},
  {label: '20-50', value: '20-50'},
  {label: '100-200', value: '100-200'},
  {label: '200-500', value: '200-500'},
  {label: '500-1500', value: '500-1500'},
  {label: '1500-3000', value: '1500-3000'},
  {label: '3000-more', value: '3000-more'},
];