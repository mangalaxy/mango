import React, {Component, Fragment} from 'react';
import EmployerMenu from '../../components/Employer/EmployerMenu/EmployerMenu';
import './EmployerPage.scss';

class Employer extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <EmployerMenu currentPage={this.props.history.location.pathname}/>
          <div className="employersContent">
            
          </div>
        </Fragment>
    );
  }
}

export default Employer;