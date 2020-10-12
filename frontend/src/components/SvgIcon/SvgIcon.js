import React, {memo} from 'react';

function SvgIcon(props) {
  const {type} = props;

  return (
      <div className='svg-icon'>
        {type}
      </div>
  );
}

export default memo(SvgIcon);
