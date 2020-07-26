import React, {Component} from 'react';
import {GreenDash} from '../../Home/Home';
import './jobsByRole.scss';
import {reduxForm} from 'redux-form';
import jobRolesData from '../../../../constants/jobRolesData';
import RoleCategory from './RoleCategory/RoleCategory';
import DropDownSelect from '../../../inputs/Select/DropDownSelect/DropDownSelect';
import JobItem from "./JobItem/JobItem";
import ReactPaginate from 'react-paginate';


const mockRoleData = {
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat',
    name: 'Design Jobs',
    categories: ['Brand / Graphic Designer', 'Product Designer', 'UX Designer', 'UX Researcher', 'Visual / UI Designer']
}

const mockCountries = [
    {label: 'Ukraine', value: 'Ukraine'},
    {label: 'Germany', value: 'Germany'},
    {label: 'England', value: 'England'},
];

const mockCities = [
    {label: 'Kyiv', value: 'Kyiv'},
    {label: 'Lviv', value: 'Lviv'},
    {label: 'Odessa', value: 'Odessa'},
];

const mockIndustries = [
    {label: 'Industry1', value: 'Industry1'},
    {label: 'Industry2', value: 'Industry2'},
    {label: 'Industry3', value: 'Industry3'},
];

const mockJobs = [
    {
        id: 1,
        logo: '../../../../../assets/images/company-photo.png',
        title: 'Brand/Graphic Designer',
        company: {
            title: 'Okta, Inc.',
            employers: '20-50 employers',
            location: 'Seattle',
            category: 'Marketplace',
            description: 'Provide secure identity management and single sign-on to any application, whether in the cloud, on-premises or on a mobile device for your employees, partners and customers with okta.'
        }
    },
    {
        id: 2,
        logo: '../../../../assets/images/company-photo.png',
        title: 'Brand/Graphic Designer',
        company: {
            title: 'Okta, Inc.',
            employers: '20-50 employers',
            location: 'Seattle',
            category: 'Marketplace',
            description: 'Provide secure identity management and single sign-on to any application, whether in the cloud, on-premises or on a mobile device for your employees, partners and customers with okta.'
        }
    },
    {
        id: 3,
        logo: '../../../../assets/images/company-photo.png',
        title: 'Brand/Graphic Designer',
        company: {
            title: 'Okta, Inc.',
            employers: '20-50 employers',
            location: 'Seattle',
            category: 'Marketplace',
            description: 'Provide secure identity management and single sign-on to any application, whether in the cloud, on-premises or on a mobile device for your employees, partners and customers with okta.'
        }
    },
    {
        id: 4,
        logo: '../../../../assets/images/company-photo.png',
        title: 'Brand/Graphic Designer',
        company: {
            title: 'Okta, Inc.',
            employers: '20-50 employers',
            location: 'Seattle',
            category: 'Marketplace',
            description: 'Provide secure identity management and single sign-on to any application, whether in the cloud, on-premises or on a mobile device for your employees, partners and customers with okta.'
        }
    },

]

class JobsByRole extends Component {

  render(){
    const role = this.props.match.params.jobRole;
    const roleData = jobRolesData.find(el => el.link === role);

    if (!mockRoleData) return (
        <div className='job-role'>
          <div className='contentContainer'>
            <p>Jobs by this role not found!</p></div>
        </div>);

    const categories = mockRoleData.categories.map((item, index) => <RoleCategory category={item} key={index} />)
    const jobsList = mockJobs.map(job => <JobItem job={job} key={job.id} />)
    return (
        <div className='job-role'>
          <div className='job-role__content'>
              <div className='job-role__tags'>
                  <div className='job-role__tag'>Job Directory</div>
                  <div className='job-role__tag'>Designer</div>
              </div>
            <h3 className='job-role__title'>
              {mockRoleData.name} job
            </h3>
            <GreenDash/>
            <div className="job-role__description">
              <p>{mockRoleData.description}</p>
              <p>{mockRoleData.description}</p>
            </div>
            <div className='job-role__categories'>
              {categories}
            </div>
            <div className='job-role__filters'>
                <div className='job-role__filter'>Filter by:</div>
                <DropDownSelect
                    contentRenderer={() => 'Companies'}
                    className='job-role__filter'
                    searchable={false}
                    options={mockCountries}
                    multi={true}
                />
                <DropDownSelect
                    contentRenderer={() => 'Location'}
                    className='job-role__filter'
                    searchable={false}
                    options={mockCities}
                    multi={true}
                />
                <DropDownSelect
                    contentRenderer={() => 'Industries'}
                    className='job-role__filter'
                    searchable={false}
                    options={mockIndustries}
                    multi={true}
                />
            </div>
            <div className='jobs-list'>
                {jobsList}
                <ReactPaginate
                    pageCount={20}
                    containerClassName='jobs-pagination'
                    pageClassName='jobs-pagination__item'
                    pageLinkClassName='jobs-pagination__link'
                    activeLinkClassName='jobs-pagination__link--active'
                    onPageChange={() => {}}
                    previousLinkClassName='jobs-pagination__link--prev'
                    nextLinkClassName='jobs-pagination__link--next'
                    nextLabel=''
                    previousLabel=''
                />
            </div>
          </div>
        </div>
    );
  }

}

JobsByRole = reduxForm ({
    form: 'jobRole',
}) (JobsByRole);

export default JobsByRole;