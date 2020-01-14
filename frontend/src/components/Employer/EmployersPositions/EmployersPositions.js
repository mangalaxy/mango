import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import EmployersPositionItem from './EmployersPositionItem';
import './EmployersPositions.scss';
   
class EmployersPositions extends Component {
  render() {
    return (
      <div className="employersPositions">

        <Link to="#" className="employersPositions-btn">
          + create new position
        </Link>

        <h3 className="employersPositions-title">
          Your positions
        </h3>

        <div className="employersPositions-content">
          {this.props.jobs.map(item => (
            <EmployersPositionItem key={item.id} itemData={item} history={this.props.history} />
          ))}
        </div>
      </div>
    )
  }
}
const mapStoreToProps = (store) => {
  return {jobs: store.jobsReducer}
}
export default connect(mapStoreToProps)(EmployersPositions);