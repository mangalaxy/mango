import React from 'react';
import '../Auth.scss';
import rocket from '../../../assets/icons/rocket.svg';
import SighUpTalentForm from '../../Forms/SignUpTalentForm';

const SignUpTalent = ({handleSignIn}) => {
  return (
      <div className="authContainer">
        <div className='infoContainer talentSignUp'>
          <div className="content">
            <img src={rocket} alt=""/>
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
            <SighUpTalentForm
            />
            <p>Have an account? <span onClick={handleSignIn}>Sign in</span></p>
          </div>
        </div>
      </div>
  );

};

export default SignUpTalent;