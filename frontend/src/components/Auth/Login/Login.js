import React from 'react';
import './Login.scss';
import rocket from '../../../assets/icons/rocket.svg';
import LoginForm from '../../Forms/LoginForm';

const Login = props => {
  const {closeForm} = props;
  return (
      <div className="loginContainer">
        <div className='infoContainer'>
          <div className="content">
            <img src={rocket} alt=""/>
            <h2>Sign In</h2>
            <div>
              <span className='description'>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Excepturi hic iste pariatur perspiciatis! Accusamus cum deleniti dolor doloribus eligendi
                enim hic laboriosam maiores modi nostrum, odio quae ratione repudiandae sit!</span>
            </div>
          </div>

        </div>
        <div className='formContainer'>
          <div className="content">
            <LoginForm/>
          </div>
        </div>
      </div>
  );

};

export default Login;