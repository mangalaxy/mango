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

          <section id='mission'>
            <div className="container">
              <div className="contentRow">
                <h3>Why choose us</h3>
                {greenDash}
                <p>The ultimate platform that connects the best talents in IT
                  with topnotch companies in the industry and provides a smooth
                  way for the ideal candidate to join the best organization </p>
              </div>
              <div className="contentRow">
                <h3>Our mission</h3>
                {greenDash}
                <p>Making our platform the final place for prompt acquisition of
                  talents by companies according to talent’s tech stack and
                  experience, considering location and market expectations </p>
              </div>
              <div className="contentRow">
                <h3>What we do</h3>
                {greenDash}
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

          <section id='steps'>
            <div className="container">
              <h2>
                Five easy steps
              </h2>
              {greenDash}
              {renderStepsDiagram()}
            </div>
          </section>

        </Fragment>
    );
  }
}

export default Home;

const greenDash = <div className='greenDash'>
  <svg width="127" height="4" viewBox="0 0 127 4" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M0.925293 0.388916H126.103V3.39725H0.925293V0.388916Z" fill="#36B3A8" fillOpacity="0.9"/>
  </svg>
</div>;

const renderStepsDiagram = () => {
  const steps = [
    {
      header: 'Registration',
      text: 'Talents fill up key information about technology stack, desired location and salary expectations',
    }, {
      header: 'Application',
      text: 'After successful registration a candidate expects invitation from multiple companies',
    }, {
      header: 'Invitation',
      text: 'A few vacancies are offered and a candidate is able to choose a proper company for him',
    }, {
      header: 'Negotiation',
      text: 'A candidate has an interview in a chosen company',
    }, {
      header: 'Presentation',
      text: 'Cheers, you nailed it, the job is yours',
    },
  ];
  return (
      <div className='stepsDiagram'>
        <div className="flowLine"/>
        {steps.map((step, index)=>(
            <div className='outerCircle'>
              <div className='innerCircle'>
                <span className="index">
                {index+1}
              </span>
              </div>
            </div>
        ))}
      </div>
  );
};
