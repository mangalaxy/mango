import React from 'react';
import './greenLine.scss';

const GreenDash = ({className}) => (
    <div className={`greenDash ${className}`}>
      <svg width="127" height="4" viewBox="0 0 127 4" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M0.925293 0.388916H126.103V3.39725H0.925293V0.388916Z" fill="#36B3A8" fillOpacity="0.9"/>
      </svg>
    </div>);

export default GreenDash;
