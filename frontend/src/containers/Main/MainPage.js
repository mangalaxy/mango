// @flow
import type {Node} from 'react';
import React, {Component, Fragment} from 'react';
import MainMenu from '../../components/Main/MainMenu/MainMenu';
import {Route, Switch} from 'react-router-dom';
import routes from '../../constants/routes';
import ForTalents from '../../components/Main/ForTalents/ForTalents';
import ForEmployers from '../../components/Main/ForEmployers/ForEmployers';
import Home from '../../components/Main/Home/Home';
import Footer from '../../components/Main/Footer/Footer';
import './Main.scss';
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
import CreateProfile
  from '../../components/Profile/CreateProfile/CreateProfile';
import SignUpTalent from '../../components/Auth/SignUpTalent/SignUpTalent';
import SignUpEmployer
  from '../../components/Auth/SignUpEmployer/SignUpEmployer';


type Props = {
  history: Object,
  location: Object
}

class MainPage extends Component<Props> {
  render(): Node {
    return (
        <Fragment>
          <MainMenu
              path={this.props.location.pathname}
              openLoginForm={this.openLoginForm}
              openSignUpTalent={this.openSignUpTalent}
              openSignUpEmployer={this.openSignUpEmployer}
          />
          <div id='dialog-container'/>
          <div className="mainPageContent">
            <Switch>
              <Route exact path={routes.HOME} component={Home}/>
              <Route path={routes.FOR_TALENTS} component={ForTalents}/>
              <Route path={routes.FOR_EMPLOYERS} component={ForEmployers}/>
              <Route exact path={routes.FIND_JOB} component={JobsRolesSelect}/>
              <Route path={routes.FIND_JOB + '/:jobRole'}
                     component={JobsByRole} url={routes.FIND_JOB}/>
              <Route path={routes.ABOUT} component={About}/>
              <Route path={routes.PRIVACY_POLICY} component={PrivacyPolicy}/>
              <Route path={routes.TERMS_OF_USE} component={TermsOfUse}/>
              <Route path={routes.SUPPORT_CENTER} component={Support}/>
              <Route path={routes.BLOG} component={Blog}/>
              <Route path={'/*'} component={() => (<div>404</div>)}/>
            </Switch>
          </div>
          <Footer/>
        </Fragment>
    );
  }

  openLoginForm = () => {
    renderModal(
        <Login handleSignUpTalent={this.openSignUpTalent}
        />,
    );
  };

  openSignUpTalent = () => renderModal(
      <SignUpTalent handleSignIn={this.openLoginForm}
      />,
  );
  openSignUpEmployer = () => renderModal(
      <SignUpEmployer handleSignIn={this.openLoginForm}
      />,
  );
}

export default MainPage;