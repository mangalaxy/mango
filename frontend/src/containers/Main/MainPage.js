import React from 'react';
import {Route, Switch} from 'react-router-dom';
import routes from '../../constants/routes.json';
import {connect} from 'react-redux';
import MainMenu from '../../components/Main/MainMenu/MainMenu';
import ForTalents from '../../components/Main/ForTalents/ForTalents';
import ForEmployers from '../../components/Main/ForEmployers/ForEmployers';
import Home from '../../components/Main/Home/Home';
import Footer from '../../components/Main/Footer/Footer';
import About from '../../components/Main/About/About';
import JobsRolesSelect
  from '../../components/Main/Jobs/JobsRolesSelect/JobsRoleSelect';
import JobsByRole from '../../components/Main/Jobs/JobsByRole/JobsByRole';
import PrivacyPolicy from '../../components/Main/PrivacyPolicy/PrivacyPolicy';
import TermsOfUse from '../../components/Main/TermsOfService/TermsOfUse';
import Support from '../../components/Main/Support/Support';
import Blog from '../../components/Main/Blog/Blog';
import {renderModal} from '../../services/renderModal';
import Login from '../../components/Auth/Login/Login';
import SignUpTalent from '../../components/Auth/SignUpTalent/SignUpTalent';
import SignUpEmployer
  from '../../components/Auth/SignUpEmployer/SignUpEmployer';
import Post from '../../components/Main/Post/Post';
import MobileMenu from '../../components/Main/MobileMenu/MobileMenu';
import './Main.scss';

const MainPage = ({mobileMenuOpen, location}) => {

  const openLoginForm = () => {
    renderModal(
        <Login handleSignUpTalent={openSignUpTalent}
               handleSingUpEmployer={openSignUpEmployer}
        />,
    );
  };

  const openSignUpTalent = () => renderModal(
      <SignUpTalent handleSignIn={openLoginForm}
      />,
  );
  const openSignUpEmployer = () => renderModal(
      <SignUpEmployer handleSignIn={openLoginForm}
      />,
  );

  return (
      <div className={`main ${mobileMenuOpen && 'main--mobile-open'}`}>
        <MainMenu
            path={location.pathname}
            openLoginForm={openLoginForm}
            openSignUpTalent={openSignUpTalent}
            openSignUpEmployer={openSignUpEmployer}
        />
        {mobileMenuOpen &&
        <MobileMenu
            path={location.pathname}
            openLoginForm={openLoginForm}
            openSignUpTalent={openSignUpTalent}
            openSignUpEmployer={openSignUpEmployer}
        />
        }
        <div id='dialog-container'/>
        <div className="mainPageContent">
          <Switch>
            <Route exact path={routes.COMMON.HOME} component={Home}/>
            <Route exact path={routes.COMMON.FOR_TALENTS}
                   component={ForTalents}/>
            <Route exact path={routes.COMMON.FOR_EMPLOYERS}
                   component={ForEmployers}/>
            <Route exact path={routes.COMMON.FIND_JOB}
                   component={JobsRolesSelect}/>
            <Route exact path={routes.COMMON.FIND_JOB + '/:jobRole'}
                   component={JobsByRole} url={routes.FIND_JOB}/>
            <Route exact path={routes.COMMON.ABOUT} component={About}/>
            <Route exact path={routes.COMMON.PRIVACY_POLICY}
                   component={PrivacyPolicy}/>
            <Route exact path={routes.COMMON.TERMS_OF_USE}
                   component={TermsOfUse}/>
            <Route exact path={routes.COMMON.SUPPORT_CENTER}
                   component={Support}/>
            <Route exact path={routes.COMMON.BLOG} component={Blog}/>
            <Route exact path={routes.COMMON.POST} component={Post}/>
            {/*<Route path={'/*'} component={() => (*/}
            {/*    <div className={'commonContent'}>404</div>)}/>*/}
          </Switch>
        </div>
        <Footer/>
      </div>
  );
};

const mapStateToProps = ({mobileMenuReducer}) => {
  return {
    mobileMenuOpen: mobileMenuReducer.mobileMenuOpen,
  };
};

export default connect(mapStateToProps, null)(MainPage);
