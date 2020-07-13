import React, {useState} from 'react';
import {Link, NavLink} from 'react-router-dom';
import Down from '../../Down/Down';
import BurgerMenu from './BurgerMenu/BurgerMenu';
import routes from '../../../constants/routes.json';
import './MainMenu.scss';

const MainMenu = ({path, grey, dark, openLoginForm, openSignUpTalent, openSignUpEmployer}) => {
  const [dropdownVisible, setDropdownVisible] = useState(false);
  const showMenu = () => setDropdownVisible(true);
  const hideMenu = () => setDropdownVisible(false);

  let grayMode = !(path === routes.COMMON.HOME ||
      path === routes.COMMON.FOR_TALENTS ||
      path === routes.COMMON.FOR_EMPLOYERS || path === routes.COMMON.ABOUT ||
      path === routes.COMMON.BLOG);

  return (
      <div className={`menuContainer ${grayMode || grey && 'gray'}`}>
        <div className='logoContainer'>
          <Link to={routes.COMMON.HOME} className='logo'>
            Mango
          </Link>
        </div>
        <ul className='menuItemsContainer'>
          <li>
            <NavLink to={routes.COMMON.FOR_TALENTS}
                     activeClassName={'menuItem active'}
                     className={`menuItem ${grayMode || grey && 'gray'}`}>
              Talents
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.COMMON.FOR_EMPLOYERS}
                     activeClassName={'menuItem active'}
                     className={`menuItem ${grayMode || grey && 'gray'}`}>
              Employers
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.COMMON.FIND_JOB}
                     activeClassName={'menuItem active'}
                     className={`menuItem ${grayMode || grey && 'gray'}`}>
              Jobs
            </NavLink>
          </li>
          <li>
            <NavLink to={routes.COMMON.BLOG}
                     activeClassName={'menuItem active'}
                     className={`menuItem ${grayMode || grey && 'gray'}`}>
              Blog
            </NavLink>
          </li>
        </ul>
        <div className='menuItemsContainer authLinks'>
          <div onClick={openLoginForm}>
            <span className={`menuItem  ${grayMode || grey &&
            'gray'} authBlock left`}>Log in</span>
          </div>
          <div>
              <span className={`menuItem  ${grayMode || grey &&
              'gray'} authBlock`}
                    onClick={showMenu}>
                Sign up
                <Down active={dropdownVisible} grayMode={grayMode}/>
                <ul className={`dropdownMenu ${!dropdownVisible &&
                'hidden'} ${grayMode && 'gray'}`}
                    onMouseLeave={hideMenu}
                >
                    <li onClick={openSignUpTalent}>Talents</li>
                    <li onClick={openSignUpEmployer}>Employers</li>
                </ul>
              </span>
          </div>
        </div>
        <BurgerMenu
            openLoginForm={openLoginForm}
            openSignUpTalent={openSignUpTalent}
            openSignUpEmployer={openSignUpEmployer}
        />
      </div>
  );
};

export default MainMenu;