import React, {Component, Fragment} from 'react';
import './Home.scss';

class Home extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <section className='bannerContainer' id={'home-banner'}>
            <div className='bannerContent'>
              <h1>
                Truly changing
              </h1>
              <h1>
                the World for the better
              </h1>
              <h4>
                Connecting outstanding people with the world’s
                most innovative companies
              </h4>
            </div>
          </section>


        </Fragment>
    );
  }
}

export default Home;