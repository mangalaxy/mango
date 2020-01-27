import React from 'react';
import './Modal.scss';
import Button from '../Buttons/FormButton/FormButton';

const Modal = ({modalText, modalAction, canсelModal, okLabel='Yes', noLabel='No'}) => {
  return(
    <div className='modal-container'>
      <div className="modal">
        <p className="modal-title">
          {modalText}
        </p>
        <Button text={okLabel} onClick={modalAction} className={'modal-btn btn-red'} />
        <Button text={noLabel} onClick={canсelModal} className={'modal-btn btn-gray'}/>
      </div>
    </div>
  )
}
export default Modal;