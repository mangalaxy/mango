import React, {Component} from 'react';
import {Route} from 'react-router-dom';
import routes from '../../constants/routes';
import EmployerMenu from '../../components/Employer/EmployerMenu/EmployerMenu';
import EmployerWelcome from '../../components/Employer/EmployerWelcome/EmployerWelcome';
import EmployersPositions from '../../components/Employer/EmployersPositions/EmployersPositions';
import './EmployerPage.scss';
import EmployersCompany from './Company/CompanyPage';

class Employer extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <div className="employer-bg">
          <EmployerMenu currentPage={this.props.history.location.pathname}/>
          <Route path={routes.EMPLOYERS_WELCOME} component={EmployerWelcome}/>
          <Route path={routes.EMPLOYERS_OPEN_POSITIONS} component={EmployersPositions}/>
          <Route path={routes.EMPLOYERS_COMPANY} component={EmployersCompany}/>
        </div>
    );
  }
}

export default Employer;
