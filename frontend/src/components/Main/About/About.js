//@flow
import React, {Component, Fragment} from 'react';
import type {Node} from 'react';
import './About.scss';
import {GreenDash, Partners} from '../Home/Home';
import globe from '../../../assets/icons/globe.svg';
import planet from '../../../assets/icons/planet-earth.svg';
import city from '../../../assets/icons/city.svg';
import briefcase from '../../../assets/icons/case.svg';
import quotes from '../../../assets/icons/quotes.svg';
import {Growl} from 'primereact/growl';
import AboutContactForm from '../../Forms/AboutContactForm';

type Props = {}

const teamMembers = [
  {image: '/images/team/photo1.jpg', name: 'Angel Lee', position: 'Cmo'},
  {image: '/images/team/photo2.png', name: 'Bill  Brown', position: 'fOUNDER'},
  {image: '/images/team/photo3.jpg', name: 'Kelly Rose', position: 'cfo'},
  {
    image: '/images/team/photo4.jpg',
    name: 'Mike Stone ',
    position: 'co-founder',
  },
];

const feedback = [
  {
    header: 'Very professional team',
    text: 'Lorem ipsum dolor sit amet. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ',
    name: 'Emily Lewiz',
    position: 'Top Manager',
    image: '/images/face1.png',
  },
  {
    header: 'Quickly and efficiently',
    text: 'Lorem ipsum dolor sit amet, consectetur adig elit, sed do eiusmod tempor ididunt ut labore et dolore magnased do eiusmod tempor ididunt ut labore et dolore magna. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ',
    name: 'David McGee',
    position: 'Lead Java Software Engineer',
    image: '/images/face2.png',
  },
];

class About extends Component <Props> {
  props: Props;

  showSuccess = () => {
    this.growl.show({
      severity: 'success',
      summary: 'Success!',
      detail: 'Message submitted',
    });
  };

  showError = () => {
    this.growl.show({
      severity: 'error',
      summary: 'Error!',
      detail: 'Message submit failed',
    });
  };

  render(): Node {
    return (
        <Fragment>
          <Growl ref={(el) => this.growl = el}/>
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

          <section id='new-jobs'>
            <h4>New interesting jobs and teams are waiting for you!</h4>
            <button className='actionButton'>Apply now</button>
          </section>

          <section id='team'>
            <h2 className="mainHeaderH2">
              Our team
            </h2>
            <GreenDash/>
            <div className='teamMembersContainer'>
              {teamMembers.map(
                  (item, i) => <TeamMemberCard key={i} data={item}/>)}
            </div>
          </section>

          <section id='partners-about'>
            <h2 className="mainHeaderH2">Our partners</h2>
            <GreenDash/>
            <Partners/>
          </section>

          <section id='feedback'>
            <div className='imageFilter'/>

            <div className="content">
              <h2 className="mainHeaderH2">what clients say</h2>
              <GreenDash/>
              <div className='feedbackContainer'>
                {feedback.map(item => <Feedback data={item}/>)}
              </div>
            </div>
          </section>

          <section id='contact'>
            <div className="content">
              <div className='leftCol'>
                <div className='headerBlock'>
                  <h2 className="mainHeaderH2">get in touch</h2>
                  <GreenDash/>
                </div>
                <AboutContactForm
                    onSuccess={this.showSuccess.bind(this)}
                    onError={this.showError.bind(this)}/>
              </div>

              <div className='rightCol'>
                <div className='headerBlock'>
                  <h2 className="mainHeaderH2">how to find us</h2>
                  <GreenDash/>
                </div>
                <p>Lorem ipsum dolor sit amet, consectetur adig elit, sed do
                  eiusmod tempor ididunt ut labore et dolore
                  magna aliqua. Ut enim ad minim veniam, quis nostrud
                  exercitation ullamco laboris nisi ut aliquip. </p>
                <p className="contactData">
                  Phone: <span>+38-099-55-69-781</span>
                </p>
                <p className="contactData">
                  Email: <span>mango@gmail.com</span>
                </p>
              </div>
            </div>
          </section>
        </Fragment>
    );
  }
}

export default About;

const TeamMemberCard = (props) => {
  const {data} = props;
  return (
      <div className='memberCard'>
        <div className="imageContainer">
          <img src={data.image} alt={data.name}/>
        </div>
        <div className='memberInfo'>
          <h3>{data.name}</h3>
          <GreenDash/>
          <span>{data.position}</span>
        </div>
      </div>
  );
};

const Feedback = (props) => {
  const {data} = props;
  return (
      <div className='feedbackBlock'>
        <img className='quotes' src={quotes} alt=""/>
        <div>
          <h4>{data.header}</h4>
          <p>{data.text}</p>
        </div>
        <div className='personData'>
          <div>
            <p className='personName'>{data.name}</p>
            <p className='personPosition'>{data.position}</p>
          </div>
          <div className='imageContainer'>
            <img src={data.image} alt=""/>
          </div>
        </div>
      </div>
  );
};
