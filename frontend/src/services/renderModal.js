import React from 'react';
import * as ReactDom from 'react-dom';

export const renderModal = (modalContent) => {
  const modal = <div className="dialogContainer" onClick={onBackdropClick} id={'bg'}>
      {modalContent}
  </div>;
  ReactDom.render(modal, document.getElementById('dialog-container'));
};

export const destroyModal = () => {
  ReactDom.render(null, document.getElementById('dialog-container'));
};

const onBackdropClick  = event =>{
  let e = event || window.e;
  let target = e.target || e.srcElement;
  if (document.getElementById('bg') === target) destroyModal();
}
