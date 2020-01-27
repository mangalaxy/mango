import React, {useState} from 'react';
import {connect} from 'react-redux';
import './MatchedTalentsItem.scss';
import iconBag from '../../../assets/icons/bag.svg';
import iconHat from '../../../assets/icons/hat.svg';
import iconPencil from '../../../assets/icons/pencil.svg';
import iconList from '../../../assets/icons/list.svg';
import iconCross from '../../../assets/icons/cross.svg';
import {toggleMarkTalent} from '../../../actions/toggleMarkTalent';
import {talentDeleteAction} from '../../../actions/talentDeleteAction';
import Modal from '../../Modal/Modal';

function MatchedTalentsItem(props) {
  const [isDeleted, setIsDeleted] = useState(false);
  const [openModal, setOpenModal] = useState(false);
  function linkTo(e) {
    if (e.target.tagName === 'path' ||
        e.target.tagName === 'svg' ||
        e.target.tagName === 'IMG')
      {
      return;
    } else {
      console.log(e.target.tagName);
      props.history.push(`/employers/talent-full-profile/${props.talentData.id}`);
    }
  }

  function markTalent() {
    props.dispatch(toggleMarkTalent(props.talentData.id));
  }
  function toggleModal() {
    setOpenModal(!openModal);
  }  
  function deleteTalent() {
    props.dispatch(talentDeleteAction(props.talentData.id));
    setIsDeleted(true);
  }

  const {id, type, salary, fullName, position, skillsDescription,
        location:{city, country},
        workExperience:[...workExperienceArr],
        roleExperience:[...roleExperienceArr],
        education:[...educationArr],
        skills:[...skillsArr]} = props.talentData;
        
  let skillsList = skillsArr.map((item) => {
    return Object.values(item)[0].join(', ');
  });
  let workExp = workExperienceArr.map(item => {
    return Object.values(item).join(' ');    
  });
  let education = educationArr.map(item => {
    return Object.values(item).join(', ');    
  });
  let roleExp = roleExperienceArr.map(item => {
    return Object.values(item).join(' ');    
  });
  
  let markedColorFill, markedColorStroke;
  props.storeTalents.map(item => {
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

  return isDeleted ? null :
  (
    <div onClick={linkTo} className="matched-item">
      <div className="matched-item-talent">
        <div className="matched-item-talent-foto"
             style={{backgroundImage: `url(${require(`../../../assets/images/talents_foto/${id}.png`)})`}}>
        </div>               
        <p className="matched-item-talent-type">{type}</p>
        <p className="matched-item-talent-salary">{salary}</p>
      </div>
      <div className="matched-item-content">
        <div className="matched-item-content-name">{fullName}</div>               
        <div className="matched-item-content-location">{`${city}, ${country}`}</div>
        <div className="matched-item-content-position">{`${position} ${skillsDescription}`}</div>
        <div className="matched-item-content-group">
          <img src={iconBag}  width="18px" height="18px" alt="icon Bag"/>
          <p className="matched-item-content-group-skills">
            {workExp.join(', ')}
          </p>            
        </div>
        <div className="matched-item-content-group">
          <img src={iconHat} alt="icon Hat"/>
          <p className="matched-item-content-group-skills">
            {education.join('; ')}
          </p>            
        </div>
        <div className="matched-item-content-group">
          <img src={iconPencil} alt="icon Pencil"/>
          <p className="matched-item-content-group-skills">
            {roleExp.join(', ')}
          </p>            
        </div>
        <div className="matched-item-content-group">
          <img src={iconList} alt="icon List"/>
          <p className="matched-item-content-group-skills">
            {skillsList.join(', ')}
          </p>
        </div>
      </div>
      <div className="matched-item-control">
        <div onClick={markTalent} className="matched-item-control-box" title="Mark talent">
          <svg id="marked-elem" width="16" height="21" viewBox="0 0 16 21" fill={markedColorFill} xmlns="http://www.w3.org/2000/svg">
            <path d="M14.5 1H1V18.5L8 14.5L14.5 18.5V1Z" stroke={markedColorStroke} strokeWidth="2"/>
          </svg>
        </div>        
        <div onClick={toggleModal} className="matched-item-control-box" title="Remove talent">
          <img src={iconCross} />
        </div>
      </div>
      {openModal ?
      <Modal canсelModal={toggleModal}
             modalAction={deleteTalent}
             modalText={'Are you sure you want to remove this talent?'}
      />
      : null }
    </div>
  )  
}
const mapStoreToProps = (store) => {
  return {
    storeTalents: store.talentsReducer
  }
}
export default connect(mapStoreToProps)(MatchedTalentsItem);