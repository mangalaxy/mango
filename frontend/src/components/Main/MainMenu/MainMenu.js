//@flow
import React, {PureComponent} from 'react';
import type {Node} from 'react';
import {Link, NavLink} from 'react-router-dom';
import routes from '../../../constants/routes';
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

    const {path, grey, dark, openLoginForm, openSignUpTalent, openSignUpEmployer} = this.props;
    let grayMode = !(path === routes.HOME || path === routes.FOR_TALENTS ||
        path === routes.FOR_EMPLOYERS || path === routes.ABOUT_AS || path ===
        routes.BLOG);

    const {dropdownVisible} = this.state;
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
              <span
                  className={`menuItem  ${grayMode || grey && 'gray'} authBlock left`}>Log in</span>
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
        </div>
    );
  }
}

const Down = (props) => {
  const {active, grayMode} = props;
  return (<svg
      className={`down ${active && 'active'} ${grayMode && 'gray'}`}
      width="17" height="10" viewBox="0 0 17 10"
      fill="none" xmlns="http://www.w3.org/2000/svg">
    <path
        d="M16.8301 1.03084L15.9785 0.171877C15.865 0.057172 15.7343 0 15.5865 0C15.4391 0 15.3084 0.057172 15.1949 0.171877L8.50009 6.92438L1.80555 0.172058C1.69201 0.0573527 1.56135 0.000180444 1.41374 0.000180444C1.26607 0.000180444 1.13541 0.0573527 1.02192 0.172058L0.1704 1.03108C0.0566808 1.14555 0 1.27734 0 1.42629C0 1.57512 0.0568598 1.70692 0.1704 1.82138L8.10828 9.82824C8.22176 9.94277 8.35248 10 8.50009 10C8.6477 10 8.77818 9.94277 8.8916 9.82824L16.8301 1.82138C16.9436 1.70686 17 1.57506 17 1.42629C17 1.27734 16.9436 1.14555 16.8301 1.03084Z"
        fill="#F2F2F2"/>
  </svg>);
};
