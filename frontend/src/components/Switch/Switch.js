import React, {Component} from 'react';
import './Switch.scss';

class Switch extends Component {
  render() {
    return(
      <label className="switch">
        <input type="checkbox" name="swithOn"/>
        <span className="slider"></span>
      </label>
    )
  }
}
export default Switch;