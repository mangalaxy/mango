import React from 'react';
import SvgIcon from '../../../../SvgIcon/SvgIcon';
import {marker, companyLocation, employer} from "../../../../../assets/icons";

function JobItem(props) {
    const {job} = props;
    console.log(job.logo);
    return (
        <div className='job-item'>
            <div className='job-item__photo' style={{backgroundImage: `url(${job.logo})`}}>
            </div>
            <div className='job-item__content'>
                <h3 className='job-item__title'>{job.title}</h3>
                <div className='company-info'>
                    <div className='company-title'>{job.company.title}</div>
                    <div className='company-employers'>
                        <SvgIcon type={employer()} />
                        <div className='company-info__text'>{job.company.employers}</div>
                    </div>
                    <div className='company-location'>
                        <SvgIcon type={companyLocation()} />
                        <div className='company-info__text'>{job.company.location}</div>
                    </div>
                    <div className='company-category'>
                        <SvgIcon type={marker()} />
                        <div className='company-info__text'>{job.company.category}</div>
                    </div>
                </div>
                <div className='company-description'>
                    <span>About {job.company.title}</span> {job.company.description}
                </div>
            </div>
        </div>
    )
}

export default JobItem;