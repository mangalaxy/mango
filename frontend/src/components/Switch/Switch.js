import React from 'react';
import {connect} from 'react-redux';
import {toggleJobStatus} from '../../actions/toggleJobStatus';
import './Switch.scss';

const Switch = (props) => {
  function saveJobStatus() {
    let {isChecked, name, dispatch} = props;
    dispatch(toggleJobStatus({isChecked: !isChecked, id: name}));
  }
  return(
    <label className="switch">
      <input type="checkbox"
              name={props.name}
              defaultChecked={props.isChecked}
              onChange = {saveJobStatus}      
      />
      <span className="slider"></span>
    </label>
  )  
}
export default connect()(Switch);