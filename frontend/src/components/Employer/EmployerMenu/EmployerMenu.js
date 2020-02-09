//@flow
import React, {useState} from 'react';
import {Link, NavLink} from 'react-router-dom';
import routes from '../../../constants/routes';
import './EmloyerMenu.scss';

const EmployerMenu = ({user, theme = null}) => {
const [menuVisible, setMenuVisible] = useState(false);
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
            <NavLink to={routes.BOOKMARKED_TALENTS}
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
            <span className='menuItem authBlock left'
            onClick={()=>setMenuVisible(true)}>{user && user.fullName ?
                user.fullName :
                'USER'}
              <Down active={menuVisible}/>
            <ul className={`dropdownMenu ${!menuVisible &&
            'hidden'}`}
                onMouseLeave={()=>setMenuVisible(false)}
            >
              <li>Settings</li>
            <NavLink to={routes.EMPLOYERS_COMPANY}><li>Company</li></NavLink>
            </ul>
            </span>
          </div>
          <div>
            <span className='menuItem authBlock '>Sign out </span>
          </div>
        </div>
      </div>
  );
};
export default EmployerMenu;

const Down = ({active}) => {
  return (<svg
      className={`down ${active && 'active'}`}
      width="17" height="10" viewBox="0 0 17 10"
      fill="none" xmlns="http://www.w3.org/2000/svg">
    <path
        d="M16.8301 1.03084L15.9785 0.171877C15.865 0.057172 15.7343 0 15.5865 0C15.4391 0 15.3084 0.057172 15.1949 0.171877L8.50009 6.92438L1.80555 0.172058C1.69201 0.0573527 1.56135 0.000180444 1.41374 0.000180444C1.26607 0.000180444 1.13541 0.0573527 1.02192 0.172058L0.1704 1.03108C0.0566808 1.14555 0 1.27734 0 1.42629C0 1.57512 0.0568598 1.70692 0.1704 1.82138L8.10828 9.82824C8.22176 9.94277 8.35248 10 8.50009 10C8.6477 10 8.77818 9.94277 8.8916 9.82824L16.8301 1.82138C16.9436 1.70686 17 1.57506 17 1.42629C17 1.27734 16.9436 1.14555 16.8301 1.03084Z"
        fill="#4f4f4f"/>
  </svg>);
};