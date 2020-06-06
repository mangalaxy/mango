import React from 'react';
import rocket from '../../../assets/icons/rocket.svg';
import SignUpEmployerForm from '../../Forms/SignUpEmployerForm';

import './SignUpEmployer.scss';

const SignUpEmployer = ({handleSignIn}) => {
  return (
        <div className="signUpEmployerContainer">
          <div className='infoContainer'>
            <div className="content">
              <img src={rocket} alt="Rocket image"/>
              <h2>Sign Up</h2>
              <div>
              <span className='description'>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Excepturi hic iste pariatur perspiciatis! Accusamus cum deleniti dolor doloribus eligendi
                enim hic laboriosam maiores modi nostrum, odio quae ratione repudiandae sit!</span>
              </div>
            </div>

          </div>
          <div className='formContainer'>
            <div className="content">
              <SignUpEmployerForm/>
              <p>Have an account? <span onClick={handleSignIn}>Sign in</span></p>
            </div>
          </div>
        </div>
  )
}

export default SignUpEmployer;