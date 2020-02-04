import React from 'react';
import './Avatar.scss';

function Avatar(props) {
    const {className, img, alt, size, edit} = props;

    return (
        <div className={`avatar ${className}`} style={{ width: size, height: size }}>
            {edit && <div className='avatar__add-photo'>
                <div>change photo</div>
            </div>}
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