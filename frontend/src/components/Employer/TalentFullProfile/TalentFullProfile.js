import React from 'react';
import {connect} from 'react-redux';
import TalentData from './TalentData';
import RequestInterview from './RequestInterview';
import './TalentFullProfile.scss';

const TalentFullProfile = (props) => {
  console.log(props);
  return(
    <section className="talentProfile">
      <div className="talentProfile-container">
        <div className="talentProfile-profile">
          <TalentData talentId={props.match.params.id} history={props.history}/>
        </div>
        <div className="talentProfile-interview">
          <RequestInterview />
        </div>
      </div>
    </section>
  )
}
// const mapStoreToProps = (store) => {
//   return {
//     talents: store.talentsReducer
//   }
// }
export default connect()(TalentFullProfile);