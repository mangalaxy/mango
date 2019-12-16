import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import EmployersPositionItem from './EmployersPositionItem';
import './EmployersPositions.scss';

// arj for test:
const arjPosition = '[{"id":1,"position":"Senior Backend Engineer","city":"Seattle","country":"USA","date":"08.09.2018"},\
{"id":2,"position":"UX designer","city":"Seattle","country":"USA","date":"08.09.2018"},\
{"id":3,"position":"Front End Developer","city":"Seattle","country":"USA","date":"08.09.2018"},\
{"id":4,"position":"Product Designer Lead","city":"Seattle","country":"USA","date":"08.09.2018"},\
{"id":5,"position":"Project Manager","city":"Seattle","country":"USA","date":"08.09.2018"},\
{"id":6,"position":"Senior Backend Engineer","city":"Seattle","country":"USA","date":"08.09.2018"},\
{"id":7,"position":"Manual Engineer","city":"Seattle","country":"USA","date":"08.09.2018"},\
{"id":8,"position":"Manual Engineer","city":"Seattle","country":"USA","date":"08.09.2018"}]';

const job = (positions) => JSON.parse(positions);
   
class EmployersPositions extends Component {
  state = job(arjPosition);
  render() { 
    return (
      <div className="employersPositions">

        <Link className="employersPositions-btn">
          + create new position
        </Link>

        <h3 className="employersPositions-title">
          Your positions
        </h3>

        <div className="employersPositions-content">
          {this.state.map(item => (
            <EmployersPositionItem key={item.id} itemData={item} />
          ))}
        </div>

      </div>
    )
  }
}
export default EmployersPositions;