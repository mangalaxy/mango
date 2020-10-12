import React from 'react';
import Avatar from '../../../Avatar/Avatar'

function UpcomingItem(props) {
    const {interview} = props;
    const {img, name, position, day, time, description} = interview;
    return (
        <div className='upcomings-info'>
            <div className='upcomings-info__item'>
                <div className='upcomings-info__img'>
                    <Avatar
                        img={img}
                        alt={name}
                        size={50}
                    />
                </div>
                <div className='upcomings-info__text'>
                    <div className='upcomings-info__name'>
                        {name}
                    </div>
                    <div className='upcomings-info__position'>
                        {position}
                    </div>
                </div>
            </div>
            <div className='upcomings-info__date'>
                <div className='upcomings-info__day'>{day}</div>
                <div className='upcomings-info__cicle'></div>
                <div className='upcomings-info__time'>{time}</div>
            </div>
            <div className='upcomings-info__description'>
                {description}
            </div>
        </div>
    )
}

export default UpcomingItem;