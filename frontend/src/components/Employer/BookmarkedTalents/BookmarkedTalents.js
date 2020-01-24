import React from 'react';
import {connect} from 'react-redux';
import iconLeft from '../../../assets/icons/left.svg';
import iconRight from '../../../assets/icons/right.svg';
import MatchedTalentsItem from '../MatchedTalents/MatchedTalentsItem';
import './BookmarkedTalents.scss';

function BookmarkedTalents(props) {
  let markedTalents = props.talents.filter(item => {
    return item.bookmarked === true    
  });
  return(
    <div>
      <div className="bookmarked-container">
        {props.talents.map(item => (
            !item.bookmarked ?
            null:            
            <MatchedTalentsItem key={item.id} talentData={item} history={props.history}/>
          ))            
        }
        {markedTalents.length != 0 ?
        <p className="running-title">
          <img src={iconLeft} alt="Left icon" />
            1 2 3 ... 19 20
          <img src={iconRight} alt="Right icon" /> 
        </p> :
        <span className="bookmarked-empty">no bookmarked talents</span>
        }   
      </div>
    </div>  
  )
}
const mapStoreToProps = (store) => {
  return {
    talents: store.talentsReducer
  }
}  
export default connect(mapStoreToProps)(BookmarkedTalents);