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

          <section className='mission'>
            <div className="container">
              <div className="contentRow">
                <h3>Why choose us</h3>
                <img src="" alt=""/>
                <p>The ultimate platform that connects the best talents in IT
                  with topnotch companies in the industry and provides a smooth
                  way for the ideal candidate to join the best organization </p>
              </div>
              <div className="contentRow">
                <h3>Our mission</h3>
                <img src="" alt=""/>
                <p>Making our platform the final place for prompt acquisition of
                  talents by companies according to talent’s tech stack and
                  experience, considering location and market expectations </p>
              </div>
              <div className="contentRow">
                <h3>What we do</h3>
                <img src="" alt=""/>
                <p>For companies: We find the best candidates for your open
                  positions and provide convenient tools from talents’
                  application to taking them onboard
                </p>
                <p>
                  For talents: We deliver the one and only platform for your
                  next successful career move in IT
                </p>
              </div>
            </div>
          </section>

        </Fragment>
    );
  }
}

export default Home;