import React, {Component} from 'react';
import './MatchedTalentsItem.scss';
import iconBag from '../../../assets/icons/bag.svg';


class MatchedTalentsItem extends Component {
  render() {
    console.log(this.props);
    const {id, type, salary, fullName, position, skillsDescription,
          location:{city, country},
          experience:[...experienceArr]} = this.props.talentsData;
    console.log(experienceArr);
    return(
      <div className="matched-item">
        <div className="matched-item-talent">
          <div className="matched-item-talent-foto"
               style={{backgroundImage: `url(${require(`../../../assets/images/talents_foto/${id}.png`)})`}}>
          </div>               
          <div className="matched-item-talent-type">{type}</div>
          <div className="matched-item-talent-salary">{salary}</div>
        </div>
        <div className="matched-item-content">
          <div className="matched-item-content-name">{fullName}</div>               
          <div className="matched-item-content-location">{`${city}, ${country}`}</div>
          <div className="matched-item-content-position">{`${position} ${skillsDescription}`}</div>
          <div>
            {/* <span className="matched-item-content-icon"></span> */}
            <img src={iconBag}  width="18px" height="18px" className="matched-item-content-icon"/>
            {experienceArr.map(item => (
              <span className="matched-item-content-description">
                {`${item.company} ${item.time}`}
              </span>
            ))}            
          </div>
        </div>
      </div>
    )
  }
}
export default MatchedTalentsItem;