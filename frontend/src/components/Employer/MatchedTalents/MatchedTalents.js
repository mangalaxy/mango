import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import './MatchedTalents.scss';
import iconReturn from '../../../assets/icons/return.png';
import iconClose from '../../../assets/icons/close.png';
import MatchedTalentsItem from './MatchedTalentsItem';

class MatchedTalents extends Component {
  render() {
    const selectedId = this.props.match.params.id;
    let selectedJob = {};
    this.props.jobs.map(item => {
      if (item.id == selectedId) {        
        selectedJob = Object.assign({}, item);               
      }
    });    
    const {position, skills, type, remote, experience,
          location: {city, country}, industry, relocation,
          visaSponsor} = selectedJob;
    console.log(this.props);
    return (
      <>
        <div className="position-bg">
          <div className="position-container">
            <div className="position">
              <div className="position-content">
                <h4 className="position-content-title">Position</h4>
                <p className="position-content-title-description">{`${position}`}</p>
                <h4 className="position-content-skills">Skills</h4>
                <p className="position-content-skills-description">{`${skills}`}</p>
              </div>
              <div className="position-control">
                <Link to="/employers/open-positions" className="position-control-item">
                  <img  src={iconReturn} className="position-control-item-img1" alt="Go to back"/>
                  Return
                </Link>
                <Link to="#" className="position-control-item">
                  <img  src={iconClose} className="position-control-item-img2" alt="Close"/>
                  Close Position
                </Link>
              </div>
            </div>
            <ul className="position-data">
              <li className="position-data-item">
                <div className="position-data-item-name">Employment Type</div>
                <div className="position-data-item-description">{`${type}`}</div>
              </li>
              <li className="position-data-item">
                <div className="position-data-item-name">Remote</div>
                <div className="position-data-item-description">{`${remote}`}</div>
              </li>
              <li className="position-data-item">
                <div className="position-data-item-name">Years of experience</div>
                <div className="position-data-item-description">{`${experience}`}</div>
              </li>
              <li className="position-data-item">
                <div className="position-data-item-name">Location</div>
                <div className="position-data-item-description">{`${city}, ${country}`}</div>
              </li>
              <li className="position-data-item">
                <div className="position-data-item-name">Industry</div>
                <div className="position-data-item-description">{`${industry}`}</div>
              </li>
              <li className="position-data-item">
                <div className="position-data-item-name">Relocation</div>
                <div className="position-data-item-description">{`${relocation}`}</div>
              </li>
              <li className="position-data-item">
                <div className="position-data-item-name">Visa Sponsorship</div>
                <div className="position-data-item-description">{`${visaSponsor}`}</div>
              </li>            
            </ul>
          </div>
        </div>
        <div>
          <div className="position-container">
            <h4 className="running-title">1-50 of 128 candidates who match your criteria</h4>
            {this.props.talents.map(item => (
                <MatchedTalentsItem key={item.id} talentsData={item} />
              ))            
            }
          </div>
        </div>  
      </>  
    )
  }
}
const mapStoreToProps = (store) => {
  return {
    jobs: store.jobReducer,
    talents: store.talentsReducer
  }
}
export default connect(mapStoreToProps)(MatchedTalents);