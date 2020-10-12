import React from 'react';
import './company.scss';
import EditIcon from '../../elements/icons/EditIcon';
import GreenDash from '../../elements/GreenLine/greenLine';
import routes from '../../../constants/routes.json';
import {Link} from 'react-router-dom';
import CheckIcon from '../../elements/icons/CheckIcon';
import CircleIcon from '../../elements/icons/CircleIcon';

const VideoCard = ({src}) => {
  return <div className="video-card">
    <div className="flex-embed">
      <div className="flex-embed__ratio flex-embed__ratio--16by9">
        <img src={src} alt="promo" className="flex-embed__content"/>
      </div>
    </div>
  </div>;
};

const FeatureCard = ({title, items, itemMap}) => {
  return (
      <div className="feature-card">
        <h3>{title}</h3>
        {items.map(itemMap)}
      </div>
  );
};

const Company = ({profile}) => (
    <div className="company-profile-page">
      <header className="company-header">
        <div className="container">
          <div className="company">
            <div className="company-logo">
              <img src={profile.logo} alt="Logo"/>
            </div>
            <div className="company-info">
              <h4 className="company-title">Company</h4>
              <h2 className="company-name">{profile.name}</h2>
              <GreenDash className="line"/>
              <h5 className="company-headline">{profile.headline}</h5>
              <h5 className="company-location">{profile.address}</h5>
            </div>
          </div>
          <Link to={routes.EMPLOYERS.COMPANY_EDIT}>
            <EditIcon size={25}/>
          </Link>
        </div>
      </header>

      <div className="container">
        <div className="company-details md-8of12">
          {profile.promo &&
          <VideoCard src={profile.promo}/>
          }
          {profile.about &&
          <div>
            <h2 className='companyName'>About {profile.name}</h2>
            <p>{profile && profile.about}</p>
          </div>
          }
          {profile.images.length > 1 &&
          <div className="gallery">
            {profile.images.map((image, index) => (
                <div key={index}
                     className={`photo-container md-6of12 ${index % 2 ?
                         'right' :
                         'left'}`}>
                  <span>
                    <img src={image} alt=""/>
                  </span>
                </div>
            ))
            }
          </div>
          }
        </div>
        <div className="company-sidebar md-4of12">
          {profile.techStack &&
          <FeatureCard title="Tech stack"
                       items={profile.techStack}
                       itemMap={(item, index) => (
                           <div className='item' key={index}>
                             <span>{item}</span>
                           </div>
                       )}
          />
          }
          {profile.perks &&
          <FeatureCard title="Perks"
                       items={profile.perks}
                       itemMap={(item, index) => (
                           <div className='item' key={index}>
                             <CircleIcon size={6} color="#f44336"/>
                             <span>{item}</span>
                           </div>
                       )}/>
          }
          {profile.benefits &&
          <FeatureCard title="Benefits"
                       items={profile.benefits}
                       itemMap={(item, index) => (
                           <div className='item' key={index}>
                             <CheckIcon size={16} color="#4caf50"/>
                             <span>{item}</span>
                           </div>
                       )}

          />
          }
          {profile.links &&
          <FeatureCard title="Links"
                       items={profile.links}
                       itemMap={(item, index) => (
                           <div className='item' key={index}>
                             <Link to={item} replace><span>{item}</span></Link>
                           </div>
                       )}/>
          }
        </div>
      </div>
    </div>
);

export default Company;
