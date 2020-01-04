import React from 'react';
import {Link} from 'react-router-dom';
import Switch from '../../Switch/Switch';
import icon from '../../../assets/icons/bag.svg';

const EmployersPositionItem = (props) => {
  let {id, position, location: {city, country}, isChecked, date} = props.itemData;
  return(
    <Link to={
        {
          pathname: `/employers/matched-talents`,
          jobData: props.itemData
        }
      } 
          className="employersPositions-item">

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

    </Link>
  )
}
export default EmployersPositionItem;