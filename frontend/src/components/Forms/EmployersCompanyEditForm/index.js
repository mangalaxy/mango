import React from 'react';
import {Formik} from 'formik';
import * as Yup from 'yup';
import EmployersCompanyEditFormView from './EmployersCompanyEditFormView';

const companySchema = Yup.object().shape({
  name: Yup.string().min(2, 'Minimum 2 symbols').max(50, 'Maximum 30 symbols').required('Required field'),
});

const EmployersCompanyEditForm = ({companyProfile, onSuccess, employersRanges, industries}) => (
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
            initialValues={{
              ...companyProfile,
              employersRanges: employersRanges,
              industries: industries,
            }}
      />
);

export default EmployersCompanyEditForm;



