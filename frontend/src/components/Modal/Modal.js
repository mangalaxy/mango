import React from 'react';
import './Modal.scss';
import Button from '../Buttons/FormButton/FormButton';

function Modal(props) {
  return(
    <div className='modal-container'>
      <div className="modal">
        <p className="modal-title">
          Are you sure you want to remove this talent?
        </p>
        <Button text={'Yes'} onClick={props.deleteTalent} className={'modal-btn btn-red'} />
        <Button text={'Cancel'} onClick={props.canсelModal} className={'modal-btn btn-gray'}/>
      </div>
    </div>
  )
}
export default Modal;