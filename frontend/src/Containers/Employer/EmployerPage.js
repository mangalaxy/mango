import React, {Component, Fragment} from 'react';
import EmployerMenu from '../../Components/Employer/EmployerMenu/EmployerMenu';

class Employer extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <EmployerMenu/>
          <h3>Employer Home Page</h3>
        </Fragment>
    );
  }
}

export default Employer;