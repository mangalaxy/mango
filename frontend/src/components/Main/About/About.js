//@flow
import React, {Component, Fragment} from 'react';
import type {Node} from 'react';
import './About.scss';
import {GreenDash, Partners} from '../Home/Home';
import globe from '../../../assets/icons/globe.svg';
import planet from '../../../assets/icons/planet-earth.svg';
import city from '../../../assets/icons/city.svg';
import briefcase from '../../../assets/icons/case.svg';

type Props = {}

class About extends Component <Props> {
  props: Props;

  render(): Node {
    return (
        <Fragment>
          <div className='bannerContainer' id='about-banner'>
            <div className="imageFilter"/>
            <div className='bannerContent'>
              <h1>About us</h1>
            </div>
          </div>

          <section id='what-we-do'>
            <div className="container">
              <div className="contentContainer">
                <h2 className='mainHeaderH2'>What we do?</h2>
                <GreenDash/>
                <div className="row">
                  <div className="iconContainer">
                    <img src={globe} alt=""/>
                  </div>
                  <div className='rowContent'>
                    <h4 className="rowHeader">Lorem ipsum 1</h4>
                    <p className="rowText">Lorem ipsum dolor sit amet,
                      consectetur
                      adipisicing elit. Expedita fuga modi nisi obcaecati
                      sapiente. Accusamus deleniti dolorum harum illum vero?</p>
                  </div>
                </div>
                <div className="row">
                  <div className="iconContainer">
                    <img src={city} alt=""/>
                  </div>
                  <div className='rowContent'>
                    <h4 className="rowHeader">Lorem ipsum 1</h4>
                    <p className="rowText">Lorem ipsum dolor sit amet,
                      consectetur
                      adipisicing elit. Blanditiis doloremque ducimus fugiat
                      incidunt ipsum labore pariatur quaerat quidem ratione
                      sunt!
                      Blanditiis, error id impedit inventore optio quae
                      recusandae
                      repellat sapiente!</p>
                  </div>
                </div>
                <div className="row">
                  <div className="iconContainer">
                    <img src={briefcase} alt=""/>
                  </div>
                  <div className='rowContent'>
                    <h4 className="rowHeader">Lorem ipsum 1</h4>
                    <p className="rowText">Lorem ipsum dolor sit amet,
                      consectetur
                      adipisicing elit. Expedita fuga modi nisi obcaecati
                      sapiente. Accusamus deleniti dolorum harum illum vero?</p>
                  </div>
                </div>
              </div>
              <div className="imageContainer">
                <img src="/images/men-hand-up.png" alt=""/>
              </div>
            </div>
          </section>

          <section id='numbers'>
            <div className="container">
              <div className="column">
                <div className="iconContainer">
                  <img src={city} alt=""/>
                </div>
                <h3>24</h3>
                <span>Hi-Tech cities</span>
              </div>
              <div className="column">
                <div className="iconContainer">
                  <img src={briefcase} alt=""/>
                </div>
                <h3>20</h3>
                <span>Startup ecosystems</span>
              </div>

              <div className="column">
                <div className="iconContainer">
                  <img src={globe} alt=""/>
                </div>
                <h3>25 mln</h3>
                <span>Professional specialists</span>
              </div>

              <div className="column">
                <div className="iconContainer">
                  <img src={planet} alt=""/>
                </div>
                <h3>6</h3>
                <span>Continents</span>
              </div>
            </div>
          </section>

          <section id='mission'>
            <div className="container">
              <div className="imageContainer">
                <img src="/images/woman.png" alt=""/>
              </div>
              <div className="contentContainer">
                <h2 className='mainHeaderH2'>Our mission</h2>
                <GreenDash/>
                <div className="row">
                  <div className="iconContainer">
                    <img src={planet} alt=""/>
                  </div>
                  <div className='rowContent'>
                    <h4 className="rowHeader">Lorem ipsum 1</h4>
                    <p className="rowText">Lorem ipsum dolor sit amet,
                      consectetur
                      adipisicing elit. Expedita fuga modi nisi obcaecati
                      sapiente. Accusamus deleniti dolorum harum illum vero?</p>
                  </div>
                </div>
                <div className="row">
                  <div className="iconContainer">
                    <img src={planet} alt=""/>
                  </div>
                  <div className='rowContent'>
                    <h4 className="rowHeader">Lorem ipsum 1</h4>
                    <p className="rowText">Lorem ipsum dolor sit amet,
                      consectetur
                      adipisicing elit. Blanditiis doloremque ducimus fugiat
                      incidunt ipsum labore pariatur quaerat quidem ratione
                      sunt!
                      Blanditiis, error id impedit inventore optio quae
                      recusandae
                      repellat sapiente!</p>
                  </div>
                </div>
                <div className="row">
                  <div className="iconContainer">
                    <img src={planet} alt=""/>
                  </div>
                  <div className='rowContent'>
                    <h4 className="rowHeader">Lorem ipsum 1</h4>
                    <p className="rowText">Lorem ipsum dolor sit amet,
                      consectetur
                      adipisicing elit. Expedita fuga modi nisi obcaecati
                      sapiente. Accusamus deleniti dolorum harum illum vero?</p>
                  </div>
                </div>
              </div>

            </div>
          </section>
        </Fragment>
    );
  }
}

export default About;
