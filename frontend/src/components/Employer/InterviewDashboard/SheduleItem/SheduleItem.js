import React from 'react';
import Avatar from '../../../Avatar/Avatar';
import InterviewBurgerMenu from '../InterviewBurgerMenu/InterviewBurgerMenu';

function SheduleItem(props) {
    const {img, name, position, interviews} = props.talent;

    return (
        <div className='inreview-dashboard__shedule-item'>
            <div className='shedule-talent-info'>
                <Avatar
                    img={img}
                    alt={name}
                    size={70}
                />
                <div className='shedule-talent-info__text'>
                    <div className='shedule-talent-info__name'>
                        {name}
                    </div>
                    <div className='shedule-talent-info__position'>
                        {position}
                    </div>
                </div>
            </div>
            <div className='shedule-talent-status'>No interviews yet</div>
            <InterviewBurgerMenu/>
        </div>
    )
};

export default SheduleItem;