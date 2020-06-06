import React from 'react';
import {Link, NavLink} from 'react-router-dom';
import routes from '../../../constants/routes';

import './EmloyerMenu.scss';

const EmployerMenu = ({ user, theme = null }) => {
  return (
      <div className={`employerMenuContainer ${theme === 'white' && 'white'}`}>
        <div>
          <Link to={routes.EMPLOYERS_HOME} className='logo'>
            Mango
          </Link>
        </div>
        <ul className='menuItemsContainer'>
          <li>
            <NavLink to={routes.EMPLOYERS_OPEN_POSITIONS}
                     className={'menuItem'}
                     activeClassName={'menuItem active'}
            >Positions</NavLink>
          </li>
          <li>
            <NavLink to={routes.EMPLOYERS_FIND_TALENT}
                     className={'menuItem'}
                     activeClassName={'menuItem active'}>
              Find talent
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.EMPLOYERS_BOOKMARKED}
                     className={'menuItem'}
                     activeClassName={'menuItem active'}>
              Bookmarked
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.EMPLOYERS_INTERVIEWS}
                     className={'menuItem'}
                     activeClassName={'menuItem active'}>
              Interviews
            </NavLink>
          </li>
        </ul>
        <div className='menuItemsContainer'>
          <div>
            <span className='menuItem authBlock left'>{user && user.fullName ?
                user.fullName :
                'USER'}</span>
          </div>
          <div>
            <span className='menuItem authBlock'>Sign out</span>
          </div>
        </div>
      </div>
  );
};
export default EmployerMenu;
