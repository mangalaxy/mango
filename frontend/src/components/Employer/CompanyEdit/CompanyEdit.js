import React from 'react';
import EmployersCompanyEditForm from '../../Forms/EmployersCompanyEditForm';

const CompanyEdit = ({companyProfile, onSaveChanges}) => (
    <div className='companyPage'>
      <EmployersCompanyEditForm
          companyProfile={companyProfile}
          onSuccess={onSaveChanges}
          employersRanges={employersRanges}
          industries={industries}
      />
    </div>
);

export default CompanyEdit;

const employersRanges = [
  {label: '0-20', value: '0-20'},
  {label: '20-50', value: '20-50'},
  {label: '100-200', value: '100-200'},
  {label: '200-500', value: '200-500'},
  {label: '500-1500', value: '500-1500'},
  {label: '1500-3000', value: '1500-3000'},
  {label: '3000-more', value: '3000-more'},
];

const industries = [
  {label: 'Energy', value: 'Energy'},
  {label: 'Finance/Insurance', value: 'Finance/Insurance'},
  {label: 'Platform', value: 'Platform'},
  {label: 'Sales and Marketing', value: 'Sales and Marketing'},
  {label: 'Telecommunications', value: 'Telecommunications'},
]