import React, {Component} from 'react';
import Company from '../../../components/Employer/Company/Company';
import CompanyEdit from '../../../components/Employer/CompanyEdit/CompanyEdit';
import routes from '../../../constants/routes';

const mockCompanyProfile = {
  logo: 'https://dynamic.brandcrowd.com/asset/logo/baaa7dd7-2811-4603-9a8a-bd2f6d79f312/logo?v=4',
  name: 'dragon innovation',
  headline: 'Leading the hardware revolution',
  location: 'Alewife center, Cambridge, Massachusets',
  employersCountRange: '20-50',
  category: 'Hardware',
  images: [
    'http://www.techaxisinc.com/wp-content/uploads/2018/01/techaxis-about-hero-cropped.jpg',
    'http://www.rimoldiecf.com/sites/default/files/styles/square_crop_medium-clean/public/DC6A6276-Modifica.jpg?itok=AmHpOuAv',
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

class EmployersCompany extends Component {
  state = {
    companyProfile: mockCompanyProfile,
  };

  render() {
    const {companyProfile} = this.state;
    const editMode = this.props.match.params.mode === 'edit';
    return (
        <div>
          {editMode ? <CompanyEdit companyProfile={companyProfile} onSaveChanges={()=>this.onSaveCompanyProfile()}/> :
              <Company companyProfile={companyProfile}/>}
        </div>
    );
  }

  onSaveCompanyProfile = (profile) => {
    console.log('SAVED!');
    this.setState({companyProfile: profile});
    this.props.history.push(routes.EMPLOYERS_COMPANY)
  }
}

export default EmployersCompany;


