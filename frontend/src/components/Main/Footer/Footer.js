import React from 'react';
import './Footer.scss';
import {Link, NavLink} from 'react-router-dom';
import routes from '../../../constants/routes.json';

const Footer = () => (
    <div className='footer'  ref={(el) => {window.footer = el;}} >
      <div className='footer__top-section'>
        <div className='logo-container'>
          <Link to={routes.COMMON.HOME} className='logo'>Mangostart</Link>
        </div>
        <ul className='menuItemsContainer'>
          <li>
            <NavLink to={routes.COMMON.FOR_TALENTS}
                     activeClassName={'menuItem active'}
                     className={'menuItem'}>
              Talents
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.COMMON.FOR_EMPLOYERS}
                     activeClassName={'menuItem active'}
                     className={'menuItem'}>
              Employers
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.COMMON.FIND_JOB}
                     activeClassName={'menuItem active'}
                     className={'menuItem'}>
              Jobs
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.COMMON.BLOG}
                     activeClassName={'menuItem active'}
                     className={'menuItem'}>
              Blog
            </NavLink>
          </li>
        </ul>
      </div>
      <ul className='itemsGroup'>
        <li>
          <Link to={routes.COMMON.ABOUT} className='menuItem'>
            About us
          </Link>
        </li>
        <li>
          <Link to={routes.COMMON.SUPPORT_CENTER} className='menuItem'>
            Support
          </Link></li>
        <li>
          <Link to={routes.COMMON.PRIVACY_POLICY} className='menuItem'>
            Privacy
          </Link>
        </li>
        <li>
          <Link to={routes.COMMON.TERMS_OF_USE} className='menuItem'>
            Terms
          </Link>
        </li>
      </ul>
      <p className='copyright'>Mango &copy; 2019 All rights reserved</p>
    </div>
);

export default Footer;