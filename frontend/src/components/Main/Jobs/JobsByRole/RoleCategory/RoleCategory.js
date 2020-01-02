import React, {useState} from 'react';

function RoleCategory(props) {
    const {category} = props;
    const [selected, setSelected] = useState(false);
    const click = (e) => {
        e.preventDefault();
        setSelected(!selected);
    }

    return (
        <button className={
            `job-role__category
             ${selected && 'job-role__category--selected'}
            `}
                onClick={click}
        >
            {category}
        </button>
    )
}

export default RoleCategory;