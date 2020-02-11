import React from 'react';
import EmployersCompanyEditForm from '../../Forms/EmployersCompanyEditForm';

const CompanyEdit = ({companyProfile, onSaveChanges}) => (
    <div className='companyPage'>
      <EmployersCompanyEditForm
          companyProfile={companyProfile}
          onSuccess={onSaveChanges}/>
    </div>
);

export default CompanyEdit;