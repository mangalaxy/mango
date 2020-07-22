import React, {Fragment} from "react";
import WorksDiagram from "./WorksDiagram";

import "./ForEmloyers.scss";

const ForEmployers = () => {
  return (
        <Fragment>
          <div className="bannerContainer" id='for-employers-banner'>
            <div className="bannerContent md-6of12">
              <h1>Our advantages</h1>
              <ul>
                <li>The best technical talents from around the world.</li>
                <li>Fast search for appropriate candidates on average saves five
                  working days.
                </li>
                <li>Exceptional opportunity to replenish your team with worthy
                  candidates.
                </li>
                <li>Excellent feedback and assistance of career advocates in
                  finding the necessary candidates.
                </li>
              </ul>
            </div>
          </div>

          <section id='market'>
            <h2 className='mainHeaderH2'>Our market of candidates</h2>
            <div className="mapContainer">
              <img src="/images/image_map.png" alt="World map"/>
              <img src="/images/markers_on_map.png" alt="" className='markers'/>
            </div>
            <p>Time to start getting <br/> candidates on board</p>
            <button className="accent-btn">Apply now</button>
          </section>

          <section id='how-works'>
            <h2 className='mainHeaderH2'>How it works</h2>
            <WorksDiagram/>
          </section>

          <section id='price'>
            <div className="textContainer">
              <h3>Transparent hire at a fair price</h3>
              <p>Payment for hiring a worthy candidate on the platform fully meets
                your expectations</p>
            </div>
          </section>

          <section className="say-hello">
            <h4>Say hello to a new candidate</h4>
            <button className="accent-btn">Get started</button>
          </section>
        </Fragment>
  );
}

export default ForEmployers;
