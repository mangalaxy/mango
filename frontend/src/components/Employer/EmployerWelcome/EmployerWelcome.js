import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import './EmployerWelcome.scss';

class EmployerWelcome extends Component {
  render() {
    return (
      <div className="employer-welcome">
        <h3 className="employer-welcome-title">
          Hello, Mike Will!
        </h3>
        <p className="employer-welcome-text">
          Welcome to Mango platform, where you can find 
          best hi-Tech talents according to your needs. 
        </p>
        <p className="employer-welcome-text">
          Get started your work by creating a new position 
        </p>
        <span className="employer-welcome-sign">
          Your career advocate,         
        </span>
        <span className="employer-welcome-sign">          
          Alice Ostin
        </span>
        <Link className="employer-welcome-btn">
          + create new position
        </Link>

      </div>
    )
  }
}
export default EmployerWelcome;