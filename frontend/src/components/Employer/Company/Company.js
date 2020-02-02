import React from 'react';
import './company.scss';
import EditIcon from '../../elements/icons/EditIcon';
import GreenDash from '../../elements/GreenLine/greenLine';

const Company = ({companyProfile, setEditMode}) => (
    <div className='companyPage'>
      <header className='header'>
        <div className="contentContainer">
          <div className="content">
            <div className='logoContainer'>
              <img src={companyProfile && companyProfile.logo} alt=""/>
            </div>

            <div>
              <h4 className='label'>Company</h4>
              <h2 className='companyName'>{companyProfile &&
              companyProfile.name}</h2>
              <GreenDash className='line'/>
              <h5 className='slogan'>{companyProfile &&
              companyProfile.slogan}</h5>
              <h5 className='companyLocation'>{companyProfile &&
              companyProfile.location}</h5>
            </div>
          </div>
          <div className='editButton' onClick={() => setEditMode(true)}>
            <EditIcon size={25}/>
          </div>
        </div>
      </header>

      <div className="contentContainer">
        <div className='companyInfo'>
          <img src={companyProfile && companyProfile.images &&
          companyProfile.images[0]} alt="Company main image"
               className='mainImage'/>
          <h2 className='companyName'>About {companyProfile &&
          companyProfile.name}</h2>
          <p>{companyProfile && companyProfile.aboutCompany}</p>
          <div className='companyImages'>
            {companyProfile && companyProfile.images &&
            companyProfile.images.length > 1 &&
            companyProfile.images.map((image, index) => (
                index > 0 &&
                <div key={index} className={`imageContainer ${index % 2 ?
                    'left' :
                    'right'}`}>
                  <span>
                    <img src={image} alt=""/>
                  </span>
                </div>
            ))}

          </div>
        </div>

        <div className="companySummary">
          {companyProfile && companyProfile.techStack && <>
            <h3 className='blockHeader'>Tech stack</h3>
            {companyProfile.techStack.map((item, index) => (
                <div className='blockItem'>
                  <svg width="6" height="6" viewBox="0 0 6 6" fill="none"
                       xmlns="http://www.w3.org/2000/svg">
                    <circle r="3" transform="matrix(-1 0 0 1 3 3)"
                            fill="#828282"/>
                  </svg>
                  <span>{item}</span>
                </div>
            ))}</>}

          {companyProfile && companyProfile.perks && <>
            <h3 className='blockHeader'>Perks</h3>
            {companyProfile.perks.map((item, index) => (
                <div className='blockItem'>
                  <svg width="6" height="6" viewBox="0 0 6 6" fill="none"
                       xmlns="http://www.w3.org/2000/svg">
                    <circle r="3" transform="matrix(-1 0 0 1 3 3)"
                            fill="#828282"/>
                  </svg>
                  <span>{item}</span>
                </div>
            ))}</>}

          {companyProfile && companyProfile.benefits && <>
            <h3 className='blockHeader'>Benefits</h3>
            {companyProfile.benefits.map((item, index) => (
                <div className='blockItem'>
                  <svg width="16" height="12" viewBox="0 0 16 12" fill="none"
                       xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M6.0367 11.4992C5.91101 11.4988 5.78675 11.4725 5.67163 11.4221C5.55652 11.3716 5.45301 11.298 5.36754 11.2059L0.912536 6.46669C0.746002 6.28921 0.656791 6.05285 0.664527 5.8096C0.668357 5.68916 0.695874 5.57064 0.745505 5.46083C0.795136 5.35102 0.86591 5.25206 0.953786 5.1696C1.04166 5.08714 1.14492 5.0228 1.25766 4.98025C1.37041 4.93769 1.49043 4.91776 1.61087 4.92159C1.85412 4.92933 2.08434 5.03338 2.25087 5.21085L6.02754 9.23502L13.7367 0.801686C13.8149 0.704264 13.9123 0.623866 14.0227 0.565427C14.1331 0.506988 14.2544 0.471744 14.3789 0.461858C14.5035 0.451972 14.6287 0.467654 14.747 0.507941C14.8653 0.548227 14.9741 0.612267 15.0667 0.696127C15.1593 0.779988 15.2338 0.881898 15.2856 0.9956C15.3374 1.1093 15.3654 1.23239 15.3679 1.35731C15.3704 1.48223 15.3474 1.60635 15.3001 1.72203C15.2529 1.83771 15.1826 1.94252 15.0934 2.03002L6.71504 11.1967C6.63037 11.2905 6.52723 11.3658 6.41207 11.4179C6.29691 11.4699 6.17224 11.4976 6.04587 11.4992H6.0367Z"
                        fill="#828282"/>
                  </svg>
                  <span>{item}</span>
                </div>
            ))}</>}

          {companyProfile && companyProfile.links && <>
            <h3 className='blockHeader'>Links</h3>
            {companyProfile.links.map((item, index) => (
                <div className='blockItem'>
                  <span>{item}</span>
                </div>
            ))}</>}

        </div>
      </div>
    </div>
);

export default Company;
