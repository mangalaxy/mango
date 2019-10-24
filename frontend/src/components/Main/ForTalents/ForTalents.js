import React, {Component, Fragment} from 'react';
import './ForTalents.scss';

class ForTalents extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <div className='bannerContainer' id='for-talent-banner'>
            <div className='bannerContent'>
              <h1>Our advantages</h1>
              <ul>
                <li>The best companies in the world are looking for you</li>
                <li>Speedy job search</li>
                <li> The whole process of hiring is under control (feedback, interview results, time management)</li>
                <li>Job search process is fully under your control</li>
              </ul>
            </div>
          </div>

        </Fragment>
    );
  }
}

export default ForTalents;
