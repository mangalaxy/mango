import React, {Component} from 'react';
import './MatchedTalentsItem.scss';
import iconBag from '../../../assets/icons/bag.svg';
import iconHat from '../../../assets/icons/hat.svg';
import iconPencil from '../../../assets/icons/pencil.svg';
import iconList from '../../../assets/icons/list.svg';

function SkillsListElems(props) {
  const skillElements = (Object.values(props.value));
  return(
    <ul className="matched-item-content-group-skills-elements">
      {skillElements.map((elem, index) => {
        return <li key={index} className="matched-item-content-group-description">{elem}</li>
      })}
    </ul>
  )
}

function SkillsList(props) {  
  return(
    <ul className="matched-item-content-group-skills">
      {props.skills.map((item, index) => {
        return <SkillsListElems key={index} value={item}/>
        })}
    </ul>
  )
};

class MatchedTalentsItem extends Component {
  render() {
    const {id, type, salary, fullName, position, skillsDescription,
          location:{city, country},
          experience:[...experienceArr],
          education:[...educationArr],
          skills:[...skillsArr]} = this.props.talentsData;
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
          <div className="matched-item-content-group">
            <img src={iconBag}  width="18px" height="18px"/>
            {experienceArr.map(item => (
              <span key={item.id} className="matched-item-content-group-description">
                {`${item.company} ${item.time}`}
              </span>
            ))}            
          </div>
          <div className="matched-item-content-group">
            <img src={iconHat}/>
            {educationArr.map(item => (
              <span key={item.id} className="matched-item-content-group-description">
                {`${item.institution}, ${item.specialization}, ${item.degree}`}
              </span>
            ))}            
          </div>
          <div className="matched-item-content-group">
            <img src={iconPencil}/>
            {experienceArr.map(item => (
              <span key={item.id} className="matched-item-content-group-description">
                {`${item.lastPosition} ${item.time}`}
              </span>
            ))}            
          </div>
          <div className="matched-item-content-group">
            <img src={iconList}/>
            <SkillsList skills={skillsArr} /> 
          </div>
        </div>
      </div>
    )
  }
}
export default MatchedTalentsItem;