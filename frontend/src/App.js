import React from 'react';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import routes from './constants/routes.json';
import MainPage from './containers/Main/MainPage';
import Talent from './containers/Talent/TalentHome';
import Employer from './containers/Employer/EmployerPage';
import './styles/reset.scss';
import './styles/index.scss';

const App = () => {
  const ScrollToTop = () => {
    window.scrollTo({top: 0, behavior: 'smooth'});
    return null;
  };

  return (
      <Router>
        <Route component={ScrollToTop}/>
        <Switch>
          <Route path={routes.EMPLOYERS.HOME} component={Employer}/>
          <Route path={routes.TALENT_HOME} component={Talent}/>
          <Route path={routes.COMMON.HOME} component={MainPage}/>

          {/*<Route path={routes.TALENT_HOME + '/:id/interviews'}*/}
          {/*       component={ProfileInterview}/>*/}
          {/*<Route path={routes.PROFILE_CREATE} component={CreateProfile}/>*/}
          {/*<Route path={routes.TALENT_PROFILE} component={TalentProfile}/>*/}
        </Switch>
      </Router>
  );
};

export default App;
