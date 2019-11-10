// @flow
import type {Node} from 'react';
import React, {Component, Fragment} from 'react';
import MainMenu from '../../components/Main/MainMenu/MainMenu';
import {Route, Switch} from 'react-router-dom';
import routes from '../../constants/routes';
import ForTalents from '../../components/Main/ForTalents/ForTalents';
import ForEmployers from '../../components/Main/ForEmployers/ForEmployers';
import BlogPage from './Blog/BlogPage';
import Home from '../../components/Main/Home/Home';
import Footer from '../../components/Main/Footer/Footer';
import './Main.scss';
import About from '../../components/Main/About/About';
import JobsRolesSelect
  from '../../components/Main/Jobs/JobsRolesSelect/JobsRoleSelect';
import JobsByRole from '../../components/Main/Jobs/JobsByRole/JobsByRole';

type Props = {
  history: Object
}

class MainPage extends Component<Props> {
  render(): Node {
    return (
        <Fragment>
          <MainMenu path={this.props.location.pathname}/>
          <div className="mainPageContent">
            <Switch>
              <Route exact path={routes.HOME} component={Home}/>
              <Route path={routes.FOR_TALENTS} component={ForTalents}/>
              <Route path={routes.FOR_EMPLOYERS} component={ForEmployers}/>
              <Route exact path={routes.FIND_JOB} component={JobsRolesSelect}/>
              <Route path={routes.FIND_JOB + '/:jobRole'}
                     component={JobsByRole} url={routes.FIND_JOB}/>
              <Route path={routes.BLOG} component={BlogPage}/>
              <Route path={routes.ABOUT} component={About}/>
              <Route path={'/*'} component={()=>(<div>404</div>)}/>
            </Switch>
          </div>
          <Footer/>
        </Fragment>
    );
  }
}

export default MainPage;