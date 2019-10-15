//@flow
import React from 'react';
import './styles/reset.css';
import './styles/index.scss';
import {Provider} from 'react-redux';
import {applyMiddleware, createStore} from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import reducer from './Reducers';
import MainPage from './Containers/Main/MainPage';
import routes from './constants/routes';
import Talent from './Containers/Talent/TalentHome';
import Employer from './Containers/Employer/EmployerPage';

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
