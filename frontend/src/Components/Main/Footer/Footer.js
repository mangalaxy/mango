import React, {PureComponent} from 'react';
import './Footer.scss';
import {Link} from 'react-router-dom';
import routes from '../../../constants/routes';

export default class Footer extends PureComponent {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <div className='footer'>
          <Link to={routes.HOME} className='logo'>Mango</Link>
          <div>
            <ul>
              <li><Link to={routes.ABOUT_AS}>About us</Link></li>
              <li><Link to={routes.SUPPORT_CENTER}>Support</Link></li>
              <li><Link to={routes.PRIVACY_POLICY}>Privacy</Link></li>
              <li><Link to={routes.TERMS_OF_USE}>Terms</Link></li>
            </ul>
          </div>
        </div>
    );
  }
}