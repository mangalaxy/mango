//@flow
import React, {Component, Fragment} from 'react';
import type {Node} from 'react';
import './ForEmloyers.scss';
import {GreenDash} from '../Home/Home';

class ForEmployers extends Component {
  render(): Node {
    return (
        <Fragment>
          <div className='bannerContainer' id='for-employers-banner'>
            <div className='bannerContent'>
              <h1>Our advantages</h1>
              <ul>
                <li>The best companies in the world are looking for you</li>
                <li>Speedy job search</li>
                <li> The whole process of hiring is under control (feedback,
                  interview results, time management)
                </li>
                <li>Job search process is fully under your control</li>
              </ul>
            </div>
          </div>

          <section id='market'>
            <h2 className='mainHeaderH2'> Our market of candidates</h2>
            <GreenDash/>
            <div className="mapContainer">
              <img src="/images/image_map.png" alt=""/>
              <img src="/images/markers_on_map.png" alt="" className='markers'/>
            </div>
            <p>
              Time to start getting <br/> candidates on board
            </p>
            <div>
              <button className='actionButton'>Apply now</button>
            </div>
          </section>
          <section id='how-works'>
            <h2 className='mainHeaderH2'>how it works</h2>
            <GreenDash/>
          </section>
        </Fragment>
    );
  }
}

export default ForEmployers;