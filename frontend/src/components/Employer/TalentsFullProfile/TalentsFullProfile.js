import React, {useState} from 'react';
import {connect} from 'react-redux';
// import Modal from '../../Modal/Modal';
import {toggleMarkTalent} from '../../../actions/toggleMarkTalent';
// import {talentDeleteAction} from '../../../actions/talentDeleteAction';
// import iconCross from '../../../assets/icons/cross.svg';
import Remove from '../../Remove/Remove';
import './TalentsFullProfile.scss';

function TalentsData(props) {
  console.log(props);

  // const [isDeleted, setIsDeleted] = useState(false);
  // const [openModal, setOpenModal] = useState(false);

  function markTalent() {
    props.dispatch(toggleMarkTalent(props.talentData.id));
  }
  function closeProfile() {
    props.history.goBack();
  }  
  // function toggleModal() {
  //   setOpenModal(!openModal);
  // }  
  // function deleteTalent() {
  //   props.dispatch(talentDeleteAction(props.talentData.id));
  //   setIsDeleted(true);
  // }
  const {id, type, salary, fullName, position, skillsDescription,
    location:{city, country}, bookmarked,
    workExperience:[...workExperienceArr],
    roleExperience:[...roleExperienceArr],
    education:[...educationArr],
    skills:[...skillsArr]} = props.talentData;

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
            <Remove talentId={props.talentData.id}
                    click={closeProfile}
                    title={'Go back'}
                    history={props.history}/>        
            {/* <div onClick={toggleModal} className="talent-data-control-box" title="Remove talent">
              <img src={iconCross} />
            </div> */}
          </div>
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

function TalentsFullProfile(props) {
  // console.log(props.talents);
  const talent = props.talents.find((elem) => {
    return  +props.match.params.id === elem.id
  });
 
  return(
    <TalentsData talentData={talent} history={props.history}/>
  )
}
const mapStoreToProps = (store) => {
  return {
    talents: store.talentsReducer
  }
}
export default connect(mapStoreToProps)(TalentsFullProfile);