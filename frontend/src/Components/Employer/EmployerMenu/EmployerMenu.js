import React, {PureComponent} from 'react';
import {Link} from 'react-router-dom';
import routes from '../../../constants/routes';

export default class EmployerMenu extends PureComponent {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <div>
          <div>
            <span>
              Mango
            </span>
          </div>
          <ul>
            <li>
              <Link to={routes.FOR_TALENTS}>Positions</Link>
            </li>
            <li>
              <Link to={routes.FOR_EMPLOYERS}>
                Find talent
              </Link>
            </li>
            <li>
              <Link to={routes.FIND_JOB}>
                Bookmarked
              </Link>
            </li>
            <li>
              <Link to={routes.BLOG}>
                Interviews
              </Link>
            </li>
          </ul>
          <div>
            <div>
              <span>Username</span>
              <div></div>
            </div>
            <div>
              <span>Sign out</span>
            </div>
          </div>
        </div>
    );
  }
}