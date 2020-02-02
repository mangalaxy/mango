//@flow
import React, {Component} from 'react';
import {Route} from 'react-router-dom';
import routes from '../../constants/routes';
import EmployerMenu from '../../components/Employer/EmployerMenu/EmployerMenu';
import EmployerWelcome
  from '../../components/Employer/EmployerWelcome/EmployerWelcome';
import EmployersPositions
  from '../../components/Employer/EmployersPositions/EmployersPositions';
import './EmployerPage.scss';
import EmployersCompany from './Company/CompanyPage';
import {connect} from 'react-redux';

type Props = {
  user: {
    fullName: string
  }
}

class Employer extends Component<Props> {
  getMenuTheme = () => {
    if (this.props.location.pathname ===
        routes.EMPLOYERS_COMPANY) return 'white';
    return null;
  };

  render() {
    const {user} = this.props;
    return (
        <div className="employer-bg">
          <EmployerMenu
              user={user}
              theme={this.getMenuTheme()}
          />
          <Route path={routes.EMPLOYERS_WELCOME} component={EmployerWelcome}/>
          <Route path={routes.EMPLOYERS_OPEN_POSITIONS}
                 component={EmployersPositions}/>
          <Route path={routes.EMPLOYERS_COMPANY} component={EmployersCompany}/>
        </div>
    );
  }
}

const mapStateToProps = state => ({
  user: state.user.user,
});

const mapDispatchToProps = dispatch => ({});

export default connect(mapStateToProps)(Employer);
