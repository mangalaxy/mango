import React, {Fragment} from 'react';

function UpcomingInterview(props) {
    const {interview} = props;

    return (
        <Fragment>
            <div className='interviews-upcoming__item'>
                <div className='interviews-upcoming__tags'>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Company</div>
                        <div className='info-value'>{interview.company}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Position</div>
                        <div className='info-value'>{interview.position}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Salary</div>
                        <div className='info-value'>{interview.salary}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Employment</div>
                        <div className='info-value'>{interview.employment}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Location</div>
                        <div className='info-value'>{interview.location}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Interviewer</div>
                        <div className='info-value'>{interview.interviewer}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Time zone</div>
                        <div className='info-value'>{interview.timeZone}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Date  and time</div>
                        <div className='info-value'>{interview.date}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Duration</div>
                        <div className='info-value'>{interview.duration}</div>
                    </div>
                    <div className='interviews-upcoming__row'>
                        <div className='info-text'>Format</div>
                        <div className='info-value'>{interview.format}</div>
                    </div>
                </div>
                <div className='interview-text'>
                    {interview.text}
                </div>
                <div className='interviews-upcoming__footer'>
                    <button className='interview-button interview-button--reject'>decline</button>
                </div>
            </div>
        </Fragment>
    )
}

export default UpcomingInterview;