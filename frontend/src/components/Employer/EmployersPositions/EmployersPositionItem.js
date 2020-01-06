import React from 'react';
import Switch from '../../Switch/Switch';
import icon from '../../../assets/icons/bag.svg';

const EmployersPositionItem = (props) => {
  function linkTo(e) {
    if (e.target.tagName === 'SPAN' || e.target.tagName === 'INPUT') {
      return;
    } else {
      props.history.push(`/employers/matched-talents/${id}`);
    }
  }
  let {id, position, location: {city, country}, isChecked, date} = props.itemData;
  return(
    <div onClick={linkTo} className="employersPositions-item">
      <div className="employersPositions-item-icon">
        <div className="employersPositions-item-icon-box">
          <img src={icon} width="25px" height="25px" alt="bag-icon"/>
        </div>
      </div>
      <div className="employersPositions-item-description">
        <p>
          {position} <br></br>
          {city},<br></br>
          {country}          
        </p>
      </div>
      <div className="employersPositions-item-swith">
        <Switch isChecked={isChecked} name={id}/>
        <div>{date}</div>
      </div>
    </div>    
  )
}
export default EmployersPositionItem;