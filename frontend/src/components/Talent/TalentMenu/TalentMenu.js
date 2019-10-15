import React, {PureComponent} from 'react';

export default class TalentMenu extends PureComponent {
  render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
    return (
        <div>
          <div>
            <span>
              Mango
            </span>
          </div>

          <div>
            <span>Sign out</span>
          </div>
          <div></div>
        </div>
    );
  }
}