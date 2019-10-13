import React, {Component, Fragment} from 'react';
import './Home.scss'

class Home extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <div className='bannerContainer'>
            <img src="./Assets/Images/homeBG.png" alt=""/>
          </div>
          <h3>Home Page</h3>
          <h3>Home Page</h3>
          <h3>Home Page</h3>
          <h3>Home Page</h3>
          <h3>Home Page</h3>
          <h3>Home Page</h3>
        </Fragment>
    );
  }
}

export default Home;