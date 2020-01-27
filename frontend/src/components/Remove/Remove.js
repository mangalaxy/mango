import React from 'react';
import './Remove.scss';
import iconCross from '../../assets/icons/cross.svg';

const Remove = ({click, title}) => {
  return(
    <>        
      <div onClick={click} className="remove" title={title}>
        <img src={iconCross} />
      </div>
    </>
  )
}
export default Remove;