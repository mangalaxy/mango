import React, {useRef, useState} from 'react';
import './About.scss';
import {Partners} from '../Home/Home';
import globe from '../../../assets/icons/globe.svg';
import planet from '../../../assets/icons/planet-earth.svg';
import city from '../../../assets/icons/city.svg';
import briefcase from '../../../assets/icons/case.svg';
import quotes from '../../../assets/icons/quotes.svg';
import {Growl} from 'primereact/growl';
import AboutContactForm from '../../Forms/AboutContactForm';
import company from '../../../mocks/companyInfo';

const About = () => {

  const [messageSent, setMessageSent] = useState(false);
  const growl = useRef(null);

  const showSuccess = () => {
    growl.current.show({
      severity: 'success',
      summary: 'Success!',
      detail: 'Message submitted',
    });
    setTimeout(() => setMessageSent(true), 700);
  };

  const showError = () => {
    growl.current.show({
      severity: 'error',
      summary: 'Error!',
      detail: 'Message submit failed',
    });
  };

  return (
      <>
        <Growl ref={growl}/>
        <div className='bannerContainer fit-content' id='about-banner'>
          <div className="imageFilter"/>
          <div className="container">
            <div className='bannerContent'>
              <h1 className="bannerContent-title left">About us</h1>
            </div>
          </div>
        </div>

        <section id='what-we-do'>
          <div className="container">
            <div className="contentContainer">
              <h2 className='mainHeaderH2'>What we do?</h2>

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
            <div className="semiCol">
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
            </div>

            <div className="semiCol">
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
          </div>
        </section>

        <section id='mission'>
          <div className="container">
            <div className="imageContainer">
              <img src="/images/woman.png" alt=""/>
            </div>
            <div className="contentContainer">
              <h2 className='mainHeaderH2'>Our mission</h2>
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
          <button className='accent-btn'>Apply now</button>
        </section>

        <section id='team'>
          <div className="container">
            <h2 className="mainHeaderH2">
              Our team
            </h2>
            <div className='teamMembersContainer'>
              {company.teamMembers.map(
                  (item, i) => <TeamMemberCard key={i} data={item}/>)}
            </div>
          </div>
        </section>

        <section id='partners-about'>
          <div className="container">
            <h2 className="mainHeaderH2">Our partners</h2>
            <Partners/>
          </div>

        </section>

        <section id='feedback'>
          <div className='imageFilter'/>
          <div className="content">
            <h2 className="mainHeaderH2">What clients say</h2>
            <div className='feedbackContainer'>
              {company.feedback.map(
                  (item, index) => <Feedback data={item} key={index}/>)}
            </div>
          </div>
        </section>

        <section id='contact'>
          <div className="container">
            <div className="content">
              <div className='leftCol'
                   style={messageSent ? {display: 'none'} : {}}>
                <div className='headerBlock'>
                  <h2 className="mainHeaderH2">Get in touch</h2>
                </div>
                <AboutContactForm
                    onSuccess={showSuccess}
                    onError={showError}/>
              </div>

              <div className='rightCol'>
                <div className='headerBlock'>
                  <h2 className="mainHeaderH2">How to find us</h2>
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
          </div>
        </section>
      </>
  );
};

export default About;

const TeamMemberCard = ({data}) => (
    <div className='memberCard'>
      <div className="imageContainer">
        <img src={data.image} alt={data.name}/>
      </div>
      <div className='memberInfo'>
        <h3>{data.name}</h3>
        <span>{data.position}</span>
      </div>
    </div>
);

const Feedback = ({data}) => (
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

