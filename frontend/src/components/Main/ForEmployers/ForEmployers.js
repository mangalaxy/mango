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
                <li>The best technical talents from around the world</li>
                <li>Fast search for appropriate candidates on average saves five
                  working days
                </li>
                <li>Exceptional opportunity to replenish your team with worthy
                  candidates
                </li>
                <li>Excellent feedback and assistance of career advocates in
                  finding the necessary candidates
                </li>
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

          <section id='price'>
            <div className="textContainer">
              <h3>Transparent hire at a fair price</h3>
              <p>Payment for hiring a worthy candidate on the platform fully meets
                your expectations</p>
            </div>
          </section>
          <section id='say-hello'>
            <h4>Say hello to a new candidate</h4>
            <button className='actionButton'>get started</button>
          </section>
        </Fragment>
    );
  }
}

export default ForEmployers;
//TODO: некорретно отображается в Firefox, возможно, переделать принципиально по-другому
//не работает calc, возможно все завязать на %, включая круги
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
        <Fragment key={index}>
          <div className="outerCircle"
               style={{top: `${item.y}%`, left: `${item.x}%`}}>
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
          {index < data.length - 1 &&
          <svg height="100%" width="100%"
               style={{position: 'absolute', left: `0`, top: `0`}}>
            <line x1={`calc(${item.x}% + 52px)`}
                  y1={`calc(${item.y}% ${index % 2 ? '+' : '-'} 11%)`}
                  x2={`${data[index + 1].x}%`} y2={`${index % 2 ?
                data[index + 1].y - 11 :
                data[index + 1].y + 11}%`}
                  stroke='rgba(54, 179, 168, 0.9)' strokeWidth='2'/>
          </svg>}
        </Fragment>
    ))}
  </div>);
};
