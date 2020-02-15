import React, {useState} from 'react';
import {connect} from 'react-redux';
import {jobDeleteAction} from '../../../actions/jobDeleteAction';
import Switch from '../../Switch/Switch';
import iconBag from '../../../assets/icons/bag.svg';
import iconCross from '../../../assets/icons/cross.svg';
import Modal from '../../Modal/Modal';

const EmployersPositionItem = (props) => {
  const [openModal, setOpenModal] = useState(false);
  let {id, position, location: {city, country}, isChecked, date} = props.itemData;
  function LinkTo(e) {
    if(e.target.tagName !== 'INPUT' &&
       e.target.tagName !== 'SPAN' &&
       e.target.tagName !== 'IMG' &&
       isChecked
      )
    props.history.push(`/employers/matched-talents/${id}`);
  }
  function toggleModal() {
    setOpenModal(!openModal);
  }
  function removeJob() {
    props.dispatch(jobDeleteAction(+id));
  }
  return(
    <>
      <div onClick={LinkTo} className="employersPositions-item">
        <div className="employersPositions-item-icon">
          <div className="employersPositions-item-icon-box">
            <img src={iconBag} width="25px" height="25px" alt="bag icon"/>
          </div>
        </div>
        <div className="employersPositions-item-description">
          <p>
            {position} <br></br>
            {city},<br></br>
            {country}          
          </p>
        </div>
        <div className="employersPositions-item-control">
          <Switch isChecked={isChecked} name={id}/>
          <span onClick={toggleModal} className="employersPositions-item-control-close">
            <img src={iconCross} alt="cross icon" />
          </span>

          <div>{date}</div>
        </div>      
      </div>
      {!openModal ? 
        null :
        <Modal modalText = {'Are you sure you want to remove this position?'}
             canсelModal = {toggleModal}
             modalAction = {removeJob}
        />
      }      
    </>   
  )
}
export default connect()(EmployersPositionItem);