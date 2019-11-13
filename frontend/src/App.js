//@flow
import React from 'react';
import './styles/reset.css';
import './styles/index.scss';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import {Provider} from 'react-redux';
import {applyMiddleware, createStore} from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import reducer from './reducers';
import MainPage from './containers/Main/MainPage';
import routes from './constants/routes';
import Talent from './containers/Talent/TalentHome';
import Employer from './containers/Employer/EmployerPage';
import PrivacyPolicy from './pages/PrivacyPolicy/PrivacyPolicy'
import TermsOfUse from './pages/TermsOfService/TermsOfUse'
import Support from './pages/Support/Support'
import Blog from './pages/Blog/Blog'

const store = createStore(reducer, composeWithDevTools(applyMiddleware(thunk)));

type Props = {};
type State = {};

class App extends React.Component <Props, State> {
  props: Props;
  state: State = {};

  componentDidMount() {
  }

  componentWillUnmount() {
  }

  render() {
    const ScrollToTop = () => {
      window.scrollTo(0, 0);
      return null;
    };
    return (
        <Provider store={store}>
          <Router>
            <Route component={ScrollToTop} />
            <Switch>
              <Route path={routes.TALENT_HOME} component={Talent}/>
              <Route path={routes.EMPLOYERS_HOME} component={Employer}/>
              <Route path={routes.PRIVACY_POLICY} component={PrivacyPolicy}/>
              <Route path={routes.TERMS_OF_USE} component={TermsOfUse}/>
              <Route path={routes.SUPPORT_CENTER} component={Support}/>
              <Route path={routes.BLOG} component={Blog}/>
              <Route path={routes.HOME} component={MainPage}/>
            </Switch>
          </Router>
        </Provider>
    );
  }
}

export default App;
