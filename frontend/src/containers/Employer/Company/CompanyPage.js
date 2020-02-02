import React, {Component} from 'react';
import Company from '../../../components/Employer/Company/Company';

class EmployersCompany extends Component {
  state = {
    editMode: false,
    companyProfile: mockCompanyProfile,
  };

  setEditMode = (editMode) => {
    this.setState({editMode: editMode});
  };

  render() {
    const {editMode, companyProfile} = this.state;
    return (
        <div>
          {editMode ? <></> : <Company
              companyProfile={companyProfile}
              setEditMode={this.setEditMode}
          />}

        </div>
    );
  }
}

export default EmployersCompany;

const mockCompanyProfile = {
  logo: 'https://dynamic.brandcrowd.com/asset/logo/baaa7dd7-2811-4603-9a8a-bd2f6d79f312/logo?v=4',
  name: 'dragon innovation',
  slogan: 'Leading the hardware revolution',
  location: 'Alewife center, Cambridge, Massachusets',
  employersCountRange: '20-50',
  category: 'Hardware',
  images: [
    'http://www.techaxisinc.com/wp-content/uploads/2018/01/techaxis-about-hero-cropped.jpg',
    'https://careers.ti.com/wp-content/uploads/2015/06/Our-Company-_MG_9470-cropped1.jpg',
    'http://www.rimoldiecf.com/sites/default/files/styles/square_crop_medium-clean/public/DC6A6276-Modifica.jpg?itok=AmHpOuAv',
    'http://www.rimoldiecf.com/sites/default/files/styles/square_crop_medium-clean/public/rimoldi%20-%20headquarter.jpg?itok=ctReR616',
    'http://prodagi-life.ru/wp-content/uploads/2016/02/klienty-min-e1455540759857.jpg',
  ],
  aboutCompany: 'Ac felis donec et odio pellentesque diam volutpat commodo sed. Faucibus turpis in eu mi bibendum neque egestas congue. Quis risus sed vulputate odio ut. Suspendisse potenti nullam ac tortor. Auctor eu augue ut lectus arcu bibendum at varius vel. Rutrum quisque non tellus orci ac auctor augue mauris augue. Sit amet nulla facilisi morbi tempus iaculis urna id. In egestas erat imperdiet sed euismod nisi porta lorem mollis.',
  techStack: [
    'Ruby on Rails',
    'Vue',
    'Postgres',
    'Heroku',
    'Redis',
  ],
  perks: [
    'Flexible work shedule',
    'Unlimited vacation',
    'Excelent benefits',
    'Great team',
  ],
  benefits: [
    'Medical, Dental insurance',
    'Retirement',
    'Life & Disability coverage',
  ],
  links: ['dragoninovation.com'],
};
