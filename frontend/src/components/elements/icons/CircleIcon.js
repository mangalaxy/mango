import React from 'react';

const CircleIcon = ({size, color}) => {
  return (
      <svg width={size} height={size} viewBox="0 0 6 6" fill="none"
           xmlns="http://www.w3.org/2000/svg">
        <circle r="3" transform="matrix(-1 0 0 1 3 3)"
                fill={color ? color : '#000'}/>
      </svg>
  );
};

export default CircleIcon;