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


function Languages(props) {
    const {index, inputChage, select, value} = props;

    return (
        <div className='profile-form__row'>
            <div className='inputs-container'>
                <DropDownSelect
                    name={`l.${index}.language`}
                    options={mockLenguage}
                    multi={false}
                    placeholder='language'
                    halfWidth
                    value={value.languages[index].language}
                    onChange={value => select(`languages.${index}.language`, value)}
                />
                <DropDownSelect
                    name={`experience.${index}.level`}
                    options={mockLevels}
                    multi={false}
                    placeholder='level'
                    halfWidth
                    value={value.languages[index].level}
                    onChange={value => select(`languages.${index}.level`, value)}
                />
            </div>
        </div>
    )
}

export default Languages;