import React, {Component, Fragment} from 'react';

class BlogPage extends Component {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <Fragment>
          <div className='bannerContainer'>
          </div>
          <h3>Blog</h3>
        </Fragment>
    );
  }
}

export default BlogPage;