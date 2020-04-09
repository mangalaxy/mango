import React from 'react';
import Avatar from '../../../Avatar/Avatar';
import InterviewBurgerMenu from '../InterviewBurgerMenu/InterviewBurgerMenu';

function OfferItem(props) {
    const {offer} = props;
    const {img, name, position, day, time, description} = offer;

    return (
        <div className='inreview-offers__item'>
            <div className='inreview-offers__container'>
                <div className='offer-talent-info'>
                    <Avatar
                        img={img}
                        alt={name}
                        size={50}
                    />
                    <div className='offer-talent-info__text'>
                        <div className='offer-talent-info__name'>
                            {name}
                        </div>
                        <div className='offer-talent-info__position'>
                            {position}
                        </div>
                    </div>
                </div>
                <InterviewBurgerMenu/>
            </div>
            <div className='offer-talent-description'>
                {description}
            </div>
        </div>
    )
}

export default OfferItem;