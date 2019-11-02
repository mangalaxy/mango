//@flow
import React from 'react';
import './styles/reset.css';
import './styles/index.scss';
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
    return (
        <Provider store={store}>
          <Router>
            <Switch>
              <Route path={routes.TALENT_HOME} component={Talent}/>
              <Route path={routes.EMPLOYERS_HOME} component={Employer}/>
              <Route path={routes.HOME} component={MainPage}/>
            </Switch>
          </Router>
        </Provider>
    );
  }
}

export default App;
