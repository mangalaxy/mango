import React, { memo } from 'react';
import {name} from "../../assets/icons";

function SvgIcon(props) {
    const {type} = props

    return (
        <div className='svg-icon'>
            {type}
        </div>
    )
}

export default memo(SvgIcon);
