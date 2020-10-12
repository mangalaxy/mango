import React from 'react';
import './BurgerMenu.scss';

const BurgerMenu = ({onClick}) => (
    <div className='burger-container' onClick={onClick}>
      <div className='menu-button'>
        <div className='menu-button__line'/>
        <div className='menu-button__line'/>
        <div className='menu-button__line'/>
      </div>
    </div>
);

export default BurgerMenu;