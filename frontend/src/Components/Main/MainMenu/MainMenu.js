//@flow
import React, {PureComponent} from 'react';
import {Link} from 'react-router-dom';
import routes from '../../../constants/routes';
import './MainMenu.scss';

type Props = {
  currentPage: string
};

export default class MainMenu extends PureComponent<Props> {
  props: Props;

  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {

    const {currentPage} = this.props;
    return (
        <div className={'menuContainer'}>
          <div>
            <Link to={routes.HOME} className='logo'>
              Mango
            </Link>
          </div>
          <ul className='menuItemsContainer'>
            <li>
              <Link to={routes.FOR_TALENTS}
                    className={currentPage===routes.FOR_TALENTS?'menuItem active':'menuItem' }>
                Talents
              </Link>
            </li>
            <li>
              <Link to={routes.FOR_EMPLOYERS} className={currentPage===routes.FOR_EMPLOYERS?'menuItem active':'menuItem' }>
                Employers
              </Link>
            </li>
            <li>
              <Link to={routes.FIND_JOB} className={currentPage===routes.FIND_JOB?'menuItem active':'menuItem' }>
                Jobs
              </Link>
            </li>
            <li>
              <Link to={routes.BLOG} className={currentPage===routes.BLOG?'menuItem active':'menuItem' }>
                Blog
              </Link>
            </li>
          </ul>
          <div>
            <div>
              <span>Log in</span>
            </div>
            <div>
              <span>Sign in</span>
            </div>
            <div></div>
          </div>
        </div>
    );
  }
}