import React, {Fragment} from 'react';

import './ForEmloyers.scss';

// TODO: некорретно отображается в Firefox, возможно, переделать принципиально по-другому
//  не работает calc, возможно все завязать на %, включая круги
const WorksDiagram = () => {
  const data = [
    {text: 'Create position', x: 5, y: 90},
    {text: 'Analyze matches', x: 22, y: 30},
    {text: 'Choose the right candidate', x: 40, y: 70},
    {text: 'Send an Interview Request', x: 60, y: 25},
    {text: 'Have an interview', x: 78, y: 90},
    {text: 'Close a candidate', x: 95, y: 10},
  ];
  return (
      <>
        <div className='worksDiagramContainer wide'>
          {data.map((item, index) => (
              <Fragment key={index}>
                <div className="outerCircle"
                     style={{top: `${item.y}%`, left: `${item.x}%`}}>
                  <div className="innerCircle">
            <span className='order'>
              {index + 1}
              <span className='pointHeader'
                    style={{
                      transform: `translateX(-50%) translateY(${
                          index % 2 ? 'calc(-50% - 40px)' : 'calc(50% + 40px)'
                      })`,
                    }}

              >
                {item.text}
              </span>
          </span>
                  </div>
                </div>
                {index < data.length - 1 &&
                <svg height="100%" width="100%"
                     style={{position: 'absolute', left: `0`, top: `0`}}>
                  <line x1={`calc(${item.x}% + 52px)`}
                        y1={`calc(${item.y}% ${index % 2 ? '+' : '-'} 11%)`}
                        x2={`${data[index + 1].x}%`} y2={`${index % 2 ?
                      data[index + 1].y - 11 :
                      data[index + 1].y + 11}%`}
                        stroke='rgba(54, 179, 168, 0.9)' strokeWidth='2'/>
                </svg>}
              </Fragment>
          ))}
        </div>

        <div className='worksDiagramMobile'>
          {data.map((step, index) => {
            return (
                <div className='worksDiagramMobile__item' key={index}>
                  <div className={`worksDiagramMobile__item-container`}>
                    <div className={`worksDiagramMobile__text-block`}>
                      <div className='worksDiagramMobile__cycle'>
                        <div className='worksDiagramMobile__inner-cycle'>
                          {index + 1}
                        </div>
                      </div>
                      <div className='worksDiagramMobile__line'>
                        <div className={`worksDiagramMobile__step-header`}>{step.text}</div>
                      </div>
                      <div className='worksDiagramMobile__small-cycle'/>
                    </div>
                  </div>
                </div>
            );
          })}
        </div>
      </>);
};

export default WorksDiagram;