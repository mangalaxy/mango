import React from 'react';
import Button from '../Buttons/FormButton/FormButton';

import './Modal.scss';

const Modal = ({modalText, modalAction, cancelModal, okLabel='Yes', noLabel='No'}) => {
  return(
    <div className='modal-container'>
      <div className="modal">
        <p className="modal-title">
          {modalText}
        </p>
        <Button text={okLabel} onClick={modalAction} className={'modal-btn btn-red'} />
        <Button text={noLabel} onClick={cancelModal} className={'modal-btn btn-gray'} />
      </div>
    </div>
  )
};

export default Modal;