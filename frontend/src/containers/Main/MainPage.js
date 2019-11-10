// @flow
import React, {Component, Fragment} from 'react';
import MainMenu from '../../components/Main/MainMenu/MainMenu';
import {Switch, Route} from 'react-router-dom';
import routes from '../../constants/routes';
import ForTalents from '../../components/Main/ForTalents/ForTalents';
import ForEmployers from '../../components/Main/ForEmployers/ForEmployers';
import JobsPage from './Jobs/JobsPage';
import BlogPage from './Blog/BlogPage';
import Home from '../../components/Main/Home/Home';
import Footer from '../../components/Main/Footer/Footer';
import type {Node} from 'react'
import './Main.scss'
import About from '../../components/Main/About/About';

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
              <Route path={routes.FIND_JOB} component={JobsPage}/>
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
