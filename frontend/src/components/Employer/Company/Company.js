import React from 'react';
import './company.scss';
import EditIcon from '../../elements/icons/EditIcon';
import GreenDash from '../../elements/GreenLine/greenLine';

const Company = ({companyProfile}) => (
    <div className='companyPage'>
      <header className='header'>
      <div className="contentContainer">
        <div className='companyHeader'>
          <div className="content">
            <div className='logoContainer'>
              <img src={companyProfile.logo} alt=""/>
            </div>

            <div>
              <h4 className='label'>Company</h4>
              <h2 className='companyName'>{companyProfile.name}</h2>
              <GreenDash className='line'/>
              <h5 className='slogan'>{companyProfile.slogan}</h5>
              <h5 className='companyLocation'>{companyProfile.location}</h5>
            </div>
          </div>
          <div className='editButton'>
            <EditIcon size={25}/>
          </div>
        </div>
      </div>
    </header>
      <div>

      </div>
    </div>
);

export default Company;
