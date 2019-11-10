//@flow
import React, {Component} from 'react';
import {GreenDash} from '../../Home/Home';
import './jobsByRole.scss';
import {Link} from 'react-router-dom';
import routes from '../../../../constants/routes';
import type {Node} from 'react';
import jobRolesData from '../../../../constants/jobRolesData';

type Props = {}

class JobsByRole extends Component<Props> {

  render(): Node {
    const role = this.props.match.params.jobRole;
    const roleData = jobRolesData.find(el => el.link === role);

    if (!roleData) return (
        <div id='main-by-job-role'>
          <div className='contentContainer'>
            <p>Jobs by this role not found!</p></div>
        </div>);

    return (
        <div id='main-by-job-role'>
          <div className='contentContainer'>
            <h3 className="descriptionHeader">
              {roleData.name} job
            </h3>
            <GreenDash/>
            <div className="description">
              <p>{roleData.description}</p>
              <p>{roleData.description}</p>
            </div>
          </div>
        </div>
    );
  }

}

export default JobsByRole;