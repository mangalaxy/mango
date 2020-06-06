// @flow
import React from 'react';
import {GreenDash} from '../../Home/Home';
import {Link} from 'react-router-dom';
import {routes} from '../../../../constants/routes';
import jobRolesData from '../../../../constants/jobRolesData';

import './jobsRoleSelect.scss';

const JobRoleCard = ((props: { role: any }) => (
        <div className='roleItem'>
          <div className="itemHeader">
            <h4>{props.role.name}</h4>
          </div>
          <div className="itemImage">
            <img src={props.role.image}/>
          </div>
        </div>
))

const JobsRolesSelect = () => {
  return (
        <div id='main-job-role-select'>
          <div className='contentContainer'>
            <h3 className="descriptionHeader">Find a job</h3>
            <div className="description">
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Distinctio esse facilis libero minus molestiae qui suscipit!
                Cupiditate dolor doloribus earum, eius enim, magni minima modi
                pariatur quasi quia, tenetur voluptatum?</p>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aut
                consequatur deleniti dolor ex, exercitationem fugit id, impedit
                laudantium maiores nisi nobis obcaecati, optio porro quis sequi
                tenetur ut velit. Distinctio esse facilis libero minus molestiae
                qui suscipit!
                Cupiditate dolor doloribus earum, eius enim, magni minima modi
                pariatur quasi quia, tenetur voluptatum?
              </p>
            </div>
            <h2 className="mainHeaderH2">Job roles</h2>
            <GreenDash/>
            <div className="rolesContainer">
              {jobRolesData.map((role, index) => (
                    <Link key={index} to={`${routes.FIND_JOB}/${role.link}`}>
                      <JobRoleCard role={role}/>
                    </Link>
              ))}
            </div>
          </div>
        </div>
  );
};

export default JobsRolesSelect;
