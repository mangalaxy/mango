import React from 'react';
import './company.scss';
import {GreenDash} from '../../Main/Home/Home';
import SvgIcon from '../../SvgIcon/SvgIcon';
import EditIcon from '../../elements/icons/EditIcon';

const Company = ({companyProfile}) => (
    <div className='companyPage'>
      <div className="contentContainer">
        <div className='companyHeader'>
          <div className="content">
            <div className='logoContainer'>
              <img src={companyProfile.logo} alt=""/>
            </div>

            <div>
              <h4>Company</h4>
              <h2>{companyProfile.name}</h2>
              <GreenDash/>
            </div>
          </div>
          <div>
            <EditIcon/>
          </div>
        </div>
      </div>

      <div>

      </div>
    </div>
);

export default Company;
