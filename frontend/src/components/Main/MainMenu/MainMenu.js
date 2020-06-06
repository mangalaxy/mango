//@flow
import type {Node} from 'react';
import React, {PureComponent} from 'react';
import {Link, NavLink} from 'react-router-dom';
import Down from "../../Down/Down";
import BurgerMenu from './BurgerMenu/BurgerMenu'

import {routes} from '../../../constants/routes';
import './MainMenu.scss';

type Props = {
  path: string,
  openLoginForm: ()=>void
};

type State = {
  dropdownVisible: boolean
}

export default class MainMenu extends PureComponent<Props, State> {
  props: Props;
  state = {
    dropdownVisible: false,
  };

  showMenu = () => {
    this.setState({dropdownVisible: true});
  };

  hideMenu = () => {
    this.setState({dropdownVisible: false});
  };

  render(): Node {
    const { path, grey, dark, openLoginForm, openSignUpTalent, openSignUpEmployer } = this.props;
    let grayMode = !(path === routes.HOME || path === routes.FOR_TALENTS ||
          path === routes.FOR_EMPLOYERS || path === routes.ABOUT_AS || path ===
          routes.BLOG);

    const { dropdownVisible } = this.state;
    return (
          <div className={`menuContainer ${grayMode || grey && 'gray'}`}>
            <div className='logoContainer'>
              <Link to={routes.HOME} className='logo'>
                Mango
              </Link>
            </div>
            <ul className='menuItemsContainer'>
              <li>
                <NavLink to={routes.FOR_TALENTS}
                         activeClassName={'menuItem active'}
                         className={`menuItem ${grayMode || grey && 'gray'}`}>
                  Talents
                </NavLink>
              </li>
              <li>
                <NavLink to={routes.FOR_EMPLOYERS}
                         activeClassName={'menuItem active'}
                         className={`menuItem ${grayMode || grey && 'gray'}`}>
                  Employers
                </NavLink>
              </li>
              <li>
                <NavLink to={routes.FIND_JOB}
                         activeClassName={'menuItem active'}
                         className={`menuItem ${grayMode || grey && 'gray'}`}>
                  Jobs
                </NavLink>
              </li>
              <li>
                <NavLink to={routes.BLOG}
                         activeClassName={'menuItem active'}
                         className={`menuItem ${grayMode || grey && 'gray'}`}>
                  Blog
                </NavLink>
              </li>
            </ul>
            <div className='menuItemsContainer authLinks'>
              <div onClick={openLoginForm}>
                <span className={`menuItem  ${grayMode || grey && 'gray'} authBlock left`}>Log in</span>
              </div>
              <div>
              <span className={`menuItem  ${grayMode || grey && 'gray'} authBlock`}
                    onClick={this.showMenu}>
                Sign up
                <Down active={dropdownVisible} grayMode={grayMode}/>
                <ul className={`dropdownMenu ${!dropdownVisible &&
                'hidden'} ${grayMode && 'gray'}`}
                    onMouseLeave={this.hideMenu}
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
  }
}
