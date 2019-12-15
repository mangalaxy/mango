import React from 'react';

const EmployersPositionItem = (props) => {
  return(
    <div className="employers-positions-item">
      <h3 className="employers-positions-item-title">{props.itemData.position}</h3>
    </div>
  )
}
export default EmployersPositionItem;