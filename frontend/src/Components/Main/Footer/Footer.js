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
            <ul className='itemsGroup'>
              <li>
                <Link to={routes.ABOUT_AS} className='menuItem'>
                  About us
                </Link>
              </li>
              <li>
                <Link to={routes.SUPPORT_CENTER} className='menuItem'>
                  Support
                </Link></li>
              <li>
                <Link to={routes.PRIVACY_POLICY} className='menuItem'>
                  Privacy
                </Link>
              </li>
              <li>
                <Link to={routes.TERMS_OF_USE} className='menuItem'>
                  Terms
                </Link>
              </li>
            </ul>
            <p className='copyright'>Mango &copy; 2019 All rights reserved</p>
          </div>
        </div>
    );
  }
}