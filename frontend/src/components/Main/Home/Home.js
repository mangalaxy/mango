import React, {Component, Fragment} from 'react';
import './Home.scss';
import Slider from 'react-slick';

class Home extends Component {

  state = {
    jobCardData: [
      {
        name: 'UX Designer',
        city: 'Linz',
        country: 'Austria',
        daysToApply: 12,
        imageUrl: '/images/job1.png',
      },
      {
        name: 'Senior Data Scientist',
        city: 'Berlin',
        country: 'Germany',
        daysToApply: 1,
        imageUrl: '/images/job2.png',
      },
      {
        name: 'AR/VR Engineer',
        city: 'Linz',
        country: 'Austria',
        daysToApply: 12,
        imageUrl: '/images/job3.png',
      },
    ],
  };

  render() {
    const {jobCardData} = this.state;
    const settings = {
      dots: true,
      arrows: false,
      rows: 1,
      slidesPerRow: 2,
      infinite: true,
      speed: 1500,
      slidesToShow: 3,
      slidesToScroll: 3,
      autoplay: true,
      autoplaySpeed: 4000,
    };
    
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
              <div className="buttonsContainer">
                <button className='actionButton'>Apply now</button>
                <button className='actionButtonTransparent'>learn more</button>
              </div>
            </div>
          </section>

          <section id='mission'>
            <div className="container">
              <div className="contentRow">
                <h3>Why choose us</h3>
                <GreenDash/>
                <p>The ultimate platform that connects the best talents in IT
                  with topnotch companies in the industry and provides a smooth
                  way for the ideal candidate to join the best organization </p>
              </div>
              <div className="contentRow">
                <h3>Our mission</h3>
                <GreenDash/>
                <p>Making our platform the final place for prompt acquisition of
                  talents by companies according to talent’s tech stack and
                  experience, considering location and market expectations </p>
              </div>
              <div className="contentRow">
                <h3>What we do</h3>
                <GreenDash/>
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
              <h2 className='mainHeaderH2'>
                Five easy steps
              </h2>
              <GreenDash/>
              <RenderStepsDiagram/>
            </div>
          </section>

          <section id='section4'>
            <div className='image'/>
            <div className='content'>
              <p>
                New interesting projects and teams <br/>
                are waiting for you!
              </p>
              <button className='actionButton'>Apply now</button>
            </div>
          </section>

          <section id='jobRoles'>
            <h2 className="mainHeaderH2">Job roles</h2>
            <GreenDash/>
            <div className="cardContainer">
              {jobCardData && jobCardData.map(job => <JobCard job={job}/>)}
            </div>
            <button className='actionButton'>Apply now</button>
          </section>

          <section id='techCenters'>
            <h2 className="mainHeaderH2">Tech centers</h2>
            <GreenDash/>
            <div className="citiesCarouselContainer">
              <Slider {...settings}>
                {cities.map(city => <CityCard city={city}/>)}
              </Slider>

            </div>
          </section>

          <section id='partners'>
            <h2 className="mainHeaderH2">Our partners</h2>
            <GreenDash/>
            <div className="partnersContainer">
              {partnersImages.map(path => <img src={path} alt=""/>)}
            </div>
          </section>

        </Fragment>
    );
  }
}

export default Home;

const GreenDash = () => (<div className='greenDash'>
  <svg width="127" height="4" viewBox="0 0 127 4" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M0.925293 0.388916H126.103V3.39725H0.925293V0.388916Z" fill="#36B3A8" fillOpacity="0.9"/>
  </svg>
</div>);

const RenderStepsDiagram = () => {
  const steps = [
    {
      header: 'Registration',
      text: 'Talents fill up key information about technology stack, desired location and salary expectations',
      picture: require('../../../assets/images/MainPage/step1.png'),
    }, {
      header: 'Application',
      text: 'After successful registration a candidate expects invitation from multiple companies',
      picture: require('../../../assets/images/MainPage/step2.png'),
    }, {
      header: 'Invitation',
      text: 'A few vacancies are offered and a candidate is able to choose a proper company for him',
      picture: require('../../../assets/images/MainPage/step3.png'),
    }, {
      header: 'Negotiation',
      text: 'A candidate has an interview in a chosen company',
      picture: require('../../../assets/images/MainPage/step4.png'),
    }, {
      header: 'Presentation',
      text: 'Cheers, you nailed it, the job is yours',
      picture: require('../../../assets/images/MainPage/step5.png'),
    },
  ];

  return (
      <div className='stepsDiagram'>

        {steps.map((step, index) => {
          const right = !(index % 2);
          return (
              <Fragment>
                <div className='outerCircle'>
                  <div className='innerCircle'>
                    <span className="index">{index + 1}</span>
                  </div>

                  <div className={right ? 'horizonLine' : 'horizonLine left'}>
                    <div className="lineEnd"/>
                    <h3 className="stepHeader">{step.header}</h3>
                    <p className={right ? 'stepText' : 'stepText left'}>{step.text}</p>
                    <img src={step.picture} alt="" className='stepImg'/>
                  </div>
                </div>
                {index !== steps.length - 1 && <div className="flowLine"/>}
              </Fragment>
          );
        })}
      </div>
  );
};

const JobCard = (props) => {
  const {job} = props;
  return (
      <div className='jobCardMain'>
        <h3>{job.name}</h3>
        <span>{job.city}, {job.country}</span>
        <div className="imageContainer">
          <img src={job.imageUrl} alt=""/>
        </div>
        {job.daysToApply && <span>{job.daysToApply} {job.daysToApply === 1 ? 'day' : 'days'} to apply</span>}
      </div>
  );
};

const cities = [
  {name: 'New york', image: '/images/cities/new-york.png'},
  {name: 'London', image: '/images/cities/london.png'},
  {name: 'San francisco', image: '/images/cities/san-francisco.png'},
  {name: 'Hong Kong', image: '/images/cities/hong-kong.png'},
  {name: 'Kyiv', image: '/images/cities/kyiv.png'},
  {name: 'Singapure', image: '/images/cities/singapure.png'},
  {name: 'Los Angeles', image: '/images/cities/los-angeles.png'},
  {name: 'Boston', image: '/images/cities/boston.png'},
  {name: 'Tel Aviv', image: '/images/cities/tel-aviv.png'},
  {name: 'Berlin', image: '/images/cities/berlin.png'},
  {name: 'Chicago', image: '/images/cities/chicago.png'},
  {name: 'Seattle', image: '/images/cities/seattle.png'},
  {name: 'Paris', image: '/images/cities/paris.png'},
  {name: 'Austin', image: '/images/cities/austin.png'},
  {name: 'Tokyo', image: '/images/cities/tokyo.png'},
  {name: 'Toronto', image: '/images/cities/toronto.png'},
  {name: 'Amsterdam', image: '/images/cities/amsterdam.png'},
  {name: 'Sydney', image: '/images/cities/sydney.png'},
  {name: 'Atlanta', image: '/images/cities/atlanta.png'},
  {name: 'Miami', image: '/images/cities/miami.png'},
  {name: 'Stockholm', image: '/images/cities/stockholm.png'},
  {name: 'Vancouver', image: '/images/cities/vancouver.png'},
  {name: 'San Diego', image: '/images/cities/san-diego.png'},
  {name: 'Barselona', image: '/images/cities/barselona.png'},
];

const partnersImages = [
  '/images/partners/aurea.png',
  '/images/partners/bcl.jpg',
  '/images/partners/upl.png',
  '/images/partners/webelect.png',
];

const CityCard = (props) => {
  const {city} = props;
  return (
      <div className="cityContainer">
        {city && <Fragment>
          <img src={city.image} alt=""/>
          <h3>{city.name}</h3>
        </Fragment>}
      </div>
  );
};
