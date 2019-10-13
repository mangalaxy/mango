import React from 'react';
import './styles/reset.css';
import './styles/index.scss';
import {Provider} from 'react-redux';
import {applyMiddleware, createStore} from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import reducer from './Reducers';
import HomePage from './Containers/Main/HomePage';
import routes from './constants/routes';
import Talent from './Containers/Talent/TalentHome';
import Employer from './Containers/Employer/EmployerHome';

const store = createStore(reducer, composeWithDevTools(applyMiddleware(thunk)));

class App extends React.Component {

  state = {};

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
              <Route path={routes.HOME} component={HomePage}/>
            </Switch>
          </Router>
        </Provider>
    );
  }
}

export default App;
