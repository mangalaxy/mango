import React from 'react';
import {connect} from 'react-redux';
import TalentData from './TalentData';
import RequestInterview from './RequestInterview';
import './TalentFullProfile.scss';

const TalentFullProfile = (props) => {
  return(
    <section className="talentProfile">
      <div className="talentProfile-container">
        <div className="talentProfile-profile">
          <TalentData talentId={props.match.params.id} history={props.history}/>
        </div>
        <div className="talentProfile-interview">
          <RequestInterview talentId={props.match.params.id} />
        </div>
      </div>
    </section>
  )
}
export default connect()(TalentFullProfile);