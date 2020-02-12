import React from 'react';
import {connect} from 'react-redux';
// import Modal from '../../Modal/Modal';
import {toggleMarkTalent} from '../../../actions/toggleMarkTalent';
// import {talentDeleteAction} from '../../../actions/talentDeleteAction';
// import iconCross from '../../../assets/icons/cross.svg';
import Remove from '../../Remove/Remove';
import Email from '../../../assets/icons/@';
import Mphone from '../../../assets/icons/mPhone';
import Bag from '../../../assets/icons/bag';
import Hat from '../../../assets/icons/hat';
import Pencil from '../../../assets/icons/pencil';
import List from '../../../assets/icons/list';
import Location from '../../../assets/icons/location2';
import CompanyType from '../../../assets/icons/companyType';
import Earth from '../../../assets/icons/earth';
import Link from '../../../assets/icons/link';
import './TalentData.scss';

const TalentData = (props) => {
  let talentData = props.talents.filter(item =>    
    item.id === +props.talentId
  );
  
  const {id, type, salary, fullName, email, phone,
    position, skillsDescription, companyType,
    location:{city, country}, bookmarked, link,
    workExperience:[...workExperienceArr],
    roleExperience:[...roleExperienceArr],
    education:[...educationArr],
    languages:[...languagesArr],
    skills:[...skillsArr]} = talentData[0];

  let workExp = workExperienceArr.map(item => {
    return Object.values(item).join(' ');    
  });  
  let education = educationArr.map(item => {
    return Object.values(item).join(', ');    
  });  
  let roleExp = roleExperienceArr.map(item => {
    return Object.values(item).join(' ');    
  });  
  let skillsList = skillsArr.map(item => {
    return Object.values(item).join(', ');    
  });  
  let languagesList = languagesArr.map(item => {
    return Object.values(item).join(' - ');    
  });  

  const markTalent = () => props.dispatch(toggleMarkTalent(id));
  
  const closeProfile = () => props.history.goBack();
  
  // const [isDeleted, setIsDeleted] = useState(false);
  // const [openModal, setOpenModal] = useState(false);  
  // function toggleModal() {
  //   setOpenModal(!openModal);
  // }  
  // function deleteTalent() {
  //   props.dispatch(talentDeleteAction(props.talentData.id));
  //   setIsDeleted(true);
  // }

  let markedColorFill, markedColorStroke;    
  if(bookmarked) {
    markedColorFill = 'rgba(54, 179, 168, 0.9)';
    markedColorStroke = 'rgba(54, 179, 168, 0.9)';
  }
  else {
    markedColorFill = 'none';
    markedColorStroke = '#BDBDBD';
  } 
  
  // return isDeleted ? null :
  return(
    <div className="talent">
      <div className="talent-foto">   
        <div className="talent-foto-img"
            style={{backgroundImage: `url(${require(`../../../assets/images/talents_foto/${id}.png`)})`}}
        >        
        </div>
        <p className="talent-foto-type">{type}</p>
        <p className="talent-foto-salary">{salary}</p>      
      </div>
      <div className="talent-data">
        <div>
          <div className="talent-data-title">
            <p className="talent-data-title-name">{fullName}</p>
            <p className="talent-data-title-location">{`${city}, ${country}`}</p>
          </div>
          <div className="talent-data-control">
            <div onClick={markTalent} className="talent-data-control-box" title="Mark talent">
              <svg id="marked-elem" width="16" height="21" viewBox="0 0 16 21" fill={markedColorFill} xmlns="http://www.w3.org/2000/svg">
                <path d="M14.5 1H1V18.5L8 14.5L14.5 18.5V1Z" stroke={markedColorStroke} strokeWidth="2"/>
              </svg>
            </div>
            <Remove talentId={id}
                    click={closeProfile}
                    title={'Go back'}
                    history={props.history}/>        
            {/* <div onClick={toggleModal} className="talent-data-control-box" title="Remove talent">
              <img src={iconCross} />
            </div> */}
          </div>
        </div>
        <div className="talent-data-position">{`${position} ${skillsDescription}`}</div>
        <div className="talent-data-item">
          <Email />
          <p className="talent-data-item-info">{email}</p>
        </div>
        <div className="talent-data-item">
          <Mphone />
          <p className="talent-data-item-info">{phone}</p>
        </div>
        <div className="talent-data-item">
          <Bag />
          <p className="talent-data-item-info">{workExp.join(', ')}</p>
        </div>
        <div className="talent-data-item">
          <Hat />
          <p className="talent-data-item-info">{education.join('; ')}</p>
        </div>
        <div className="talent-data-item">
          <Pencil />
          <p className="talent-data-item-info">{roleExp.join(', ')}</p>
        </div>
        <div className="talent-data-item">
          <List />
          <p className="talent-data-item-info">{skillsList.join(', ')}</p>
        </div>
        <div className="talent-data-item">
          <Location />
          <p className="talent-data-item-info">{`${city}, ${country}`}</p>
        </div>
        <div className="talent-data-item">
          <CompanyType />
          <p className="talent-data-item-info">{companyType}</p>
        </div>
        <div className="talent-data-item">
          <Earth />
          <p className="talent-data-item-info">{languagesList.join(', ')}</p>
        </div>
        <div className="talent-data-item">
          <Link />
          <p className="talent-data-item-info">{link}</p>
        </div>
      </div>
      {/* {openModal ?
      <Modal canсelModal={toggleModal}
             modalAction={deleteTalent}
             modalText={'Are you sure you want to remove this talent?'}
      />
      : null } */}
    </div> 
  )
}
const mapStoreToProps = (store) => (
  {talents: store.talentsReducer}
)
export default connect(mapStoreToProps)(TalentData);