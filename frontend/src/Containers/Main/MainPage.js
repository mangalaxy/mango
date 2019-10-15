// @flow
import React, {Component, Fragment} from 'react';
import MainMenu from '../../Components/Main/MainMenu/MainMenu';
import {Switch, Route} from 'react-router-dom';
import routes from '../../constants/routes';
import ForTalents from '../../Components/Main/ForTalents/ForTalents';
import ForEmployers from '../../Components/Main/ForEmployers/ForEmployers';
import JobsPage from './Jobs/JobsPage';
import BlogPage from './Blog/BlogPage';
import Home from '../../Components/Main/Home/Home';
import Footer from '../../Components/Main/Footer/Footer';
import type {Node} from 'react'
import './Main.scss'

type Props = {
  history: Object
}

class MainPage extends Component<Props> {
  render(): Node {
    return (
        <Fragment>
          <MainMenu currentPage={this.props.history.location.pathname}/>
          <div className="mainPageContent">
            <Switch>
              <Route exact path={routes.HOME} component={Home}/>
              <Route path={routes.FOR_TALENTS} component={ForTalents}/>
              <Route path={routes.FOR_EMPLOYERS} component={ForEmployers}/>
              <Route path={routes.FIND_JOB} component={JobsPage}/>
              <Route path={routes.BLOG} component={BlogPage}/>
            </Switch>
          </div>
          <Footer/>
        </Fragment>
    );
  }
}

export default MainPage;