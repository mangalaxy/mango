import React, {Component} from 'react';
import Company from '../../../components/Employer/Company/Company';

class EmployersCompany extends Component {
  state = {
    editMode: false,
    companyProfile: mockCompanyProfile,
  };

  render() {
    const {editMode, companyProfile} = this.state;
    return (
        <div>
          {editMode ? <></> : <Company companyProfile={companyProfile}/>}

        </div>
    );
  }
}

export default EmployersCompany;

const mockCompanyProfile = {
  logo: 'https://dynamic.brandcrowd.com/asset/logo/baaa7dd7-2811-4603-9a8a-bd2f6d79f312/logo?v=4',
  name: 'dragon innovation',
  slogan: 'Leading the hardware revolution',
  address: 'Alewife center, Cambridge, Massachusets',
  employersCountRange: '20-50',
  category: 'Hardware',

};
