import React, {Component} from 'react';
import {connect} from 'react-redux';
import {toggleJobStatus} from '../../actions/toggleJobStatus';
import './Switch.scss';

class Switch extends Component {
  saveJobStatus() {
    let {isChecked, name, dispatch} = this.props;
    dispatch(toggleJobStatus({isChecked: !isChecked, id: name}));
  }
  render() {
    return(
      <label className="switch">
        <input type="checkbox"
               name={this.props.name}
               defaultChecked={this.props.isChecked}
               onChange = {this.saveJobStatus.bind(this)}      
        />
        <span className="slider"></span>
      </label>
    )
  }
}
export default connect()(Switch);