import React from 'react';
import * as ReactDom from 'react-dom';

export const renderModal = (modalContent) => {
  const modal = <div className="dialogContainer">
    {modalContent}
  </div>;
  ReactDom.render(modal, document.getElementById('dialog-container'));
};

export const destroyModal = () => {
  ReactDom.render(null, document.getElementById('dialog-container'));
};