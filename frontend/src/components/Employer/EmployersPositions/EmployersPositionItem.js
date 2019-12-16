import React from 'react';
import Switch from '../../Switch/Switch';
import icon from '../../../assets/icons/bag.svg';

const EmployersPositionItem = (props) => {
  return(
    <div className="employersPositions-item">

      <div className="employersPositions-item-icon">
        <div className="employersPositions-item-icon-box">
          <img src={icon} width="25px" height="25px"/>
        </div>
      </div>

      <div className="employersPositions-item-description">
        <p>
          {props.itemData.position} <br></br>
          {props.itemData.city},<br></br>
          {props.itemData.country}          
        </p>
      </div>

      <div className="employersPositions-item-swith">
        <Switch />
        <div>{props.itemData.date}</div>
      </div>

    </div>
  )
}
export default EmployersPositionItem;