//@flow
import React, {Component, Fragment} from 'react';
import type {Node} from 'react';

type Props = {}

class JobsPage  extends Component <Props>{

  render(): Node {
    return (
        <Fragment>
          <div className='bannerContainer'>
          </div>
          <h3>Job Page</h3>
        </Fragment>
    );
  }
}

export default JobsPage;