//@flow
import React, {PureComponent} from 'react';
import {Link} from 'react-router-dom';
import routes from '../../../constants/routes';
import './EmloyerMenu.scss';
import type{Node} from 'react';

type Props = {
  currentPage: string,
};
type State = {
  dropdownVisible: boolean
}

export default class EmployerMenu extends PureComponent <Props, State>{
  render(): Node {
    const {currentPage} = this.props;
    return (
        <div className='employerMenuContainer'>
          <div>
            <Link to={routes.EMPLOYERS_HOME} className='logo'>
              Mango
            </Link>
          </div>
          <ul className='menuItemsContainer'>
            <li>
              <Link to={routes.EMPLOYERS_OPEN_POSITIONS}
                    className={currentPage === routes.FOR_TALENTS ?
                        'menuItem active' :
                        'menuItem'}>Positions</Link>
            </li>
            <li>
              <Link to={routes.EMPLOYERS_HOME}
                    className={currentPage === routes.FOR_TALENTS ?
                        'menuItem active' :
                        'menuItem'}>
                Find talent
              </Link>
            </li>
            <li>
              <Link to={routes.EMPLOYERS_HOME}
                    className={currentPage === routes.FOR_TALENTS ?
                        'menuItem active' :
                        'menuItem'}>
                Bookmarked
              </Link>
            </li>
            <li>
              <Link to={routes.EMPLOYERS_HOME}
                    className={currentPage === routes.FOR_TALENTS ?
                        'menuItem active' :
                        'menuItem'}>
                Interviews
              </Link>
            </li>
          </ul>
          <div className='menuItemsContainer'>
            <div>
              <span className='menuItem authBlock left'>Username</span>
            </div>
            <div>
              <span className='menuItem authBlock' >Sign out</span>
            </div>
          </div>
        </div>
    );
  }
}