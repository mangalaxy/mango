import React, {Fragment} from 'react'
import DropDownSelect from "../../../../inputs/Select/DropDownSelect/DropDownSelect";

const mockLenguage = [
    {label: 'Ukraine', value: 'Ukraine'},
    {label: 'Germany', value: 'Germany'},
    {label: 'English', value: 'English'},
];

const mockLevels = [
    {label: 'beginers', value: 'beginers'},
    {label: 'intermediate', value: 'intermediate'},
    {label: 'master', value: 'master'},
];


function Languages() {
    return (
        <div className='profile-form__row'>
            <div className='inputs-container'>
                <DropDownSelect
                    name='language'
                    options={mockLenguage}
                    multi={true}
                    placeholder='language'
                    halfWidth
                />
                <DropDownSelect
                    name='level'
                    options={mockLevels}
                    multi={true}
                    placeholder='level'
                    halfWidth
                />
            </div>
        </div>
    )
}

export default Languages;