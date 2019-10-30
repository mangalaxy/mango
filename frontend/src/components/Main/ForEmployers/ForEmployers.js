//@flow
import React, {Component, Fragment} from 'react';
import type {Node} from 'react';
import './ForEmloyers.scss';
import {GreenDash} from '../Home/Home';

type Props = {}

class ForEmployers extends Component <Props> {
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
            <WorksDiagram/>
          </section>
        </Fragment>
    );
  }
}

export default ForEmployers;

const WorksDiagram = () => {
  const data = [
    {text: 'Create position', x: 5, y: 90},
    {text: 'Analyze matches', x: 22, y: 30},
    {text: 'Choose the right candidate', x: 40, y: 70},
    {text: 'Send an Interview Request', x: 60, y: 25},
    {text: 'Have an interview', x: 78, y: 90},
    {text: 'Close a candidate', x: 95, y: 10},
  ];
  return (<div className='worksDiagramContainer'>
    {data.map((item, index) => (
        <div className="outerCircle" style={{top: `${item.y}%`, left: `${item.x}%`}}>
          <div className="innerCircle">
            <span className='order'>
              {index + 1}
              <span className='pointHeader'
                    style={{
                      transform: `translateX(-50%) translateY(${
                          index % 2 ? 'calc(-50% - 40px)' : 'calc(50% + 40px)'
                      })`,
                    }}
              >
                {item.text}
              </span>
          </span>
          </div>
        </div>
    ))}
  </div>);
};
