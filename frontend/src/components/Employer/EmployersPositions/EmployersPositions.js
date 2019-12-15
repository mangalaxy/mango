import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import EmployersPositionItem from './EmployersPositionItem';
import './EmployersPositions.scss';

// arj for test:
const arjPosition = '[{"id":1,"position":"Senior Backend Engineer","city":"Seattle","country":"USA"},\
{"id":2,"position":"UX designer","city":"Seattle","country":"USA"},\
{"id":3,"position":"Front End Developer","city":"Seattle","country":"USA"},\
{"id":4,"position":"Product designer Lead","city":"Seattle","country":"USA"}]';

const job = (positions) => JSON.parse(positions);
   
class EmployersPositions extends Component {
  state = job(arjPosition);
  render() { 
    return (
      <div className="employers-positions">

        <Link className="employers-positions-btn">
          + create new position
        </Link>

        <h3 className="employers-positions-title">
          Your positions
        </h3>

        <div className="employers-positions-container">
          {this.state.map(item => (
            <EmployersPositionItem key={item.id} itemData={item} />
          ))}
        </div>

      </div>
    )
  }
}
export default EmployersPositions;