import React from 'react';
import Switch from '../../Switch/Switch';

const EmployersPositionItem = (props) => {
  return(
    <div className="employers-positions-item">

      <div className="employers-positions-item-icon">
        Icon
      </div>

      <div className="employers-positions-item-description">
        <p>
          {props.itemData.position} <br></br>
          {props.itemData.city},<br></br>
          {props.itemData.country}          
        </p>
      </div>

      <div className="employers-positions-item-swith">
        <Switch />
        <div>08.09.2018</div>
      </div>

    </div>
  )
}
export default EmployersPositionItem;