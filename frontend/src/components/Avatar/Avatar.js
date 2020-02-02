import React from 'react';
import './Avatar.scss';

function Avatar(props) {
    const {className, img, alt, size} = props;

    return (
        <div className='avatar' style={{ width: size, height: size }}>
            <img
                className='avatar__image'
                src={img}
                alt={alt}
                style={{ width: size, height: size }}
            />
        </div>
    )
};

export default Avatar;