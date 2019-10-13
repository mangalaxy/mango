import React, {Component, Fragment} from 'react';
import MainMenu from '../../Components/Main/MainMenu/MainMenu';
import {Switch, Route} from 'react-router-dom';
import routes from '../../constants/routes';
import ForTalents from '../../Components/Main/ForTalents/ForTalents';
import ForEmployers from '../../Components/Main/ForEmployers/ForEmployers';
import JobsPage from './Jobs/JobsPage';
import BlogPage from './Blog/BlogPage';
import Home from '../../Components/Main/Home/Home';

class HomePage extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <MainMenu currentPage={this.props.history.location.pathname}/>
          <Switch>
            <Route exact path={routes.HOME} component={Home}/>
            <Route path={routes.FOR_TALENTS} component={ForTalents}/>
            <Route path={routes.FOR_EMPLOYERS} component={ForEmployers}/>
            <Route path={routes.FIND_JOB} component={JobsPage}/>
            <Route path={routes.BLOG} component={BlogPage}/>

          </Switch>
        </Fragment>
    );
  }
}

export default HomePage;