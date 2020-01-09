import React from 'react';
import {connect} from 'react-redux';
import './MatchedTalentsItem.scss';
import iconBag from '../../../assets/icons/bag.svg';
import iconHat from '../../../assets/icons/hat.svg';
import iconPencil from '../../../assets/icons/pencil.svg';
import iconList from '../../../assets/icons/list.svg';
import iconCross from '../../../assets/icons/cross.svg';
import {toggleMarkTalent} from '../../../actions/toggleMarkTalent';

function SkillsListElems(props) {
  const skillElements = (Object.values(props.value)[0]);
  return(
    <ul className="matched-item-content-group-skills-elements">
      {skillElements.map((elem, index) => {
        return <li key={index} className="matched-item-content-group-skills-description">{elem}</li>
      })}
    </ul>
  )
}

function SkillsList(props) {  
  return(
    <>
      {props.skills.map((item, index) => {
        return <SkillsListElems key={index} value={item}/>
        })}
    </>
  )
};

function MatchedTalentsItem(props) {

  function markTalent() {
    props.dispatch(toggleMarkTalent(props.talentData.id));
  }

  const {id, type, salary, fullName, position, skillsDescription,
        location:{city, country},
        experience:[...experienceArr],
        education:[...educationArr],
        skills:[...skillsArr]} = props.talentData;

  let markedColorFill, markedColorStroke;
  props.markedTalents.map(item => {
    if(item.id == id) {
      if(item.bookmarked) {
        markedColorFill = 'rgba(54, 179, 168, 0.9)';
        markedColorStroke = 'rgba(54, 179, 168, 0.9)';
      }
      else {
        markedColorFill = 'none';
        markedColorStroke = '#BDBDBD';
      }
    }
  });

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
          <img src={iconBag}  width="18px" height="18px" alt="icon Bag"/>
          <ul className="matched-item-content-group-skills">
            {experienceArr.map(item => (
              <li key={item.id} className="matched-item-content-group-skills-description">
                {`${item.company} ${item.time}`}
              </li>
            ))}
          </ul>
        </div>
        <div className="matched-item-content-group">
          <img src={iconHat} alt="icon Hat"/>
          <ul className="matched-item-content-group-skills">
            {educationArr.map(item => (
              <li key={item.id} className="matched-item-content-group-skills-description">
                {`${item.institution}, ${item.specialization}, ${item.degree}`}
              </li>
            ))}
          </ul>
        </div>
        <div className="matched-item-content-group">
          <img src={iconPencil} alt="icon Pencil"/>
          <ul className="matched-item-content-group-skills">
            {experienceArr.map(item => (
              <li key={item.id} className="matched-item-content-group-skills-description">
                {`${item.lastPosition} ${item.time}`}
              </li>
            ))}
          </ul>
        </div>
        <div className="matched-item-content-group">
          <img src={iconList} alt="icon List"/>
          <ul className="matched-item-content-group-skills">
            <SkillsList skills={skillsArr} />
          </ul> 
        </div>
      </div>

      <div className="matched-item-control">
        <div onClick={markTalent} className="matched-item-control-box">
          <svg id="marked-elem" width="16" height="21" viewBox="0 0 16 21" fill={markedColorFill} xmlns="http://www.w3.org/2000/svg">
            <path d="M14.5 1H1V18.5L8 14.5L14.5 18.5V1Z" stroke={markedColorStroke} strokeWidth="2"/>
          </svg>
        </div>
        
        <div className="matched-item-control-box">
          <img src={iconCross} />
        </div>
      </div>

    </div>
  )  
}
const mapStoreToProps = (store) => {
  return {
    markedTalents: store.talentsReducer
  }
}
export default connect(mapStoreToProps)(MatchedTalentsItem);