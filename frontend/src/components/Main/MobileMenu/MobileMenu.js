import React, {Fragment, useState} from 'react';
import './MobileMenu.scss';
import routes from '../../../constants/routes.json';
import Down from '../../Down/Down';
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import {close} from '../../../assets/icons';
import {connect} from 'react-redux';
import {closeMobileMenu} from '../../../actions/mobileMenu';
import {Link, NavLink} from 'react-router-dom';

const MobileMenu = ({openLoginForm, openSignUpTalent, openSignUpEmployer, closeMobileMenu}) => {
  const [openSubmenu, toggleMenuOpen] = useState(false);

  const logIn = () => {
    openLoginForm();
    closeMobileMenu();
  };

  const signUpTalent = () => {
    openSignUpTalent();
    closeMobileMenu();
  };

  const signUpEmployer = () => {
    openSignUpEmployer();
    closeMobileMenu();
  };

  return (
      <Fragment>
        <div className='mobile-container' onClick={closeMobileMenu}/>
        <div className='mobile-content'>
          <div className='mobile-header'>
            <div className='logo' onClick={closeMobileMenu}>
              <Link to={routes.COMMON.HOME} className='logo'>
                Mangostart
              </Link>
            </div>
            <div className='mobile-close' onClick={closeMobileMenu}><SvgIcon
                type={close()}/></div>
          </div>
          <ul className='mobile-menu'>
            <NavLink to={routes.COMMON.FOR_TALENTS} onClick={closeMobileMenu}
                     activeClassName={'mobile-menu__link_active'}
                     className='mobile-menu__link'>
              <li className='mobile-menu__item'>
                Talents
              </li>
            </NavLink>

            <NavLink to={routes.COMMON.FOR_EMPLOYERS} onClick={closeMobileMenu}
                     activeClassName={''}
                     className='mobile-menu__link'>
              <li className='mobile-menu__item'> Employers</li>
            </NavLink>

            <NavLink to={routes.COMMON.FIND_JOB} onClick={closeMobileMenu}
                     activeClassName={''}
                     className='mobile-menu__link'>
              <li className='mobile-menu__item'> Jobs</li>
            </NavLink>

            <NavLink to={routes.COMMON.BLOG} onClick={closeMobileMenu}
                     activeClassName={''}
                     className='mobile-menu__link'>
              <li className='mobile-menu__item'> Blog</li>
            </NavLink>

            <li className='mobile-menu__item' onClick={logIn}>
              Log in
            </li>
            <li className='mobile-menu__item'
                onClick={() => toggleMenuOpen(!openSubmenu)}>
              <div>Sign up
                <Down className={`dropdown-chevron ${openSubmenu ?
                    'rotated' :
                    ''}`}/>
              </div>
            </li>
            {openSubmenu && <>
              <li className='mobile-menu__item mobile-menu__item--submenu'
                  onClick={signUpTalent}>Talents
              </li>
              <li className='mobile-menu__item mobile-menu__item--submenu'
                  onClick={signUpEmployer}>Employers
              </li>
            </>
            }
          </ul>
        </div>
      </Fragment>
  );
};

export default MobileMenu;

