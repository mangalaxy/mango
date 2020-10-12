import React, {Fragment} from 'react';
import SvgIcon from '../../SvgIcon/SvgIcon';
import {employer} from "../../../assets/icons";

const InterviewRequest = ({ request }) => {
    return (
        <Fragment>
            <div className='interviews-requests__item'>
                <div className='company-information'>
                    <div className='interview-left'>
                        <div className='info-text'>Company</div>
                        <div className='company-title'>{request.companyTitle}</div>
                        <div className='interview-tags'>
                            <SvgIcon type={employer()} />
                            <div className='subscribe-text'>{request.employers}</div>
                            <div className='tag-circle'></div>
                            <div className='subscribe-text'>{request.companyCategory}</div>
                        </div>
                        <div className='info-text'>Position</div>
                        <div className='position-tags'>
                            <div className='position-tags__row'>
                                <div className='subscribe-text subscribe-text--margin-none'>{request.position}</div>
                                <div className='tag-circle'></div>
                                <div className='subscribe-text'>{request.salary}</div>
                            </div>
                            <div className='position-tags__row'>
                                <div className='subscribe-text subscribe-text--margin-none'>{request.degree}</div>
                                <div className='tag-circle'></div>
                                <div className='subscribe-text'>{request.workType}</div>
                            </div>
                        </div>
                        <div className='interview-left'></div>
                    </div>
                    <div className='interview-right'>
                        <img className='company-logo' src={`${request.companyLogo}`} alt=""/>
                    </div>
                </div>
                <hr className='interviews-line'/>
                <div className='interview-footer'>
                    <div className='interview-text'>
                        {request.text}
                    </div>
                    <div className='interview-buttons'>
                        <button className='interview-button interview-button--accept'>accept</button>
                        <button className='interview-button interview-button--reject'>reject</button>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}

export default InterviewRequest;