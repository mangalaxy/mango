import React, {Component, Fragment} from 'react';

class ForTalents extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <div className='bannerContainer'>
            <img src="./Assets/Images/main_talent_bg.png" alt=""/>
          </div>
          <h3>For Talents Page</h3>
        </Fragment>
    );
  }
}

export default ForTalents;