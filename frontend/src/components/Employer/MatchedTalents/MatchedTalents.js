import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import './MatchedTalents.scss';
import iconReturn from '../../../assets/icons/return.png';
import iconClose from '../../../assets/icons/close.png';
   
class MatchedTalents extends Component {
  render() {
    return (
      <div className="position-bg">
        <div className="position-container">
          <div class="position">
            <div className="position-content">
              <h4 className="position-title">Position</h4>
              <p className="position-title-description">senior backend engineer</p>
              <h4 className="position-skills">Skills</h4>
              <p className="position-skills-description">angular JS, JavaScript,
               NodeJS/ Express, MySQL, PHP, Python, Django, AWS, PyChart, Num Py</p>
            </div>
            <div className="position-control">
              <Link className="position-control-item">
                <img  src={iconReturn} className="position-control-item-img1"/>
                Return
              </Link>
              <Link className="position-control-item">
                <img  src={iconClose} className="position-control-item-img2"/>
                Close Position
              </Link>
            </div>
          </div>
          <div className="position-data">
            {/* data content */}
          </div>
        </div>
      </div>
    )
  }
}
const mapStoreToProps = (store) => {
  return {jobs: store.jobReducer}
}
export default connect(mapStoreToProps)(MatchedTalents);