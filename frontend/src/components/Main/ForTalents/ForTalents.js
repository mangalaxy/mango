import React, {Fragment} from 'react';
import './ForTalents.scss';
import {GreenDash, Partners} from '../Home/Home';

const ForTalents = () =>{
    return (
        <Fragment>
          <div className='bannerContainer' id='for-talent-banner'>
            <div className="imageFilter"/>
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

          <section id='our-help'>
            <div className="contentBox">
              <h3 className='mainHeaderH3'>help from our career advocates</h3>
              <GreenDash/>
              <ul>
                <li>Professional advice on creating a strong impression on
                  potential employers
                </li>
                <li>Objective assessment of companies and selection the most
                  suitable for you
                </li>
                <li>Support and follow-up of your interviews throughout the
                  negotiation process, until getting a job offer
                </li>
                <li>Expert assessment of global trends in the development of
                  projects and tendencies in wage growth
                </li>
              </ul>
            </div>
            <div className="imageContainer">
              <img src="/images/advocate.png" alt=""/>
            </div>
          </section>

          <section id='goal'>
            <div className="imageContainer"/>
            <div className='contentContainer'>
              <h4>What are you waiting for?</h4>
              <button className='actionButton'>Apply now</button>
            </div>
          </section>

          <section id='how-it-works'>
            <div className='contentContainer'>
              <h2 className='mainHeaderH2'>how it works</h2>
              <GreenDash/>
              <p>
                Show your skills and aspirations to work for influential
                companies, and we will help set up your profile
              </p>
              <p>
                Accept interview requests from leading companies and your
                chances of getting a desired job skyrocket
              </p>
            </div>
            <div className='imageContainer'>
              <img src="/images/how_it_works.png" alt=""/>
            </div>
          </section>

          <section id='why-trusted'>
            <h2 className="mainHeaderH2">WHY WE ARE TRUSTED</h2>
            <GreenDash/>
            <ul>
              <li>
                Platform that allows you to avoid intermediaries between you
                and potential employers
              </li>
              <li>
                Individual approach to every talent
              </li>
              <li>
                Free registration and easy access to negotiation with employers
              </li>
              <li>
                Involvement with most suitable projects and companies based on
                your tech stack and experience
              </li>
              <li>
                Vast selection of open positions from leading companies
              </li>
            </ul>
          </section>

          <section id='global'>
            <h2 className='mainHeaderH2'>global business</h2>
            <GreenDash/>
            <Partners/>
          </section>
        </Fragment>
    );
}

export default ForTalents;
