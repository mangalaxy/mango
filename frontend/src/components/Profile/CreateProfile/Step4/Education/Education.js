import React from 'react'
import Calendar from "../../../../inputs/Calendar/Calendar";
import TextInput from "../../../../inputs/TextInput/TextInput";

function Education(props) {
    const {index, inputChage, select, value} = props;

    return (
        <div className='profile-form__row'>
            <div className='inputs-container'>
                <TextInput
                    name={`education.${index}.institution`}
                    type='text'
                    placeholder='Institution'
                    halfWidth
                    onChange={value => inputChage(value)}
                    value={value.education[index].institution}
                />
                <TextInput
                    name={`education.${index}.spesialization`}
                    type='text'
                    placeholder='Specialization'
                    halfWidth
                    onChange={value => inputChage(value)}
                    value={value.education[index].spesialization}
                />
                <TextInput
                    name={`education.${index}.degree`}
                    type='text'
                    placeholder='Degree'
                    halfWidth
                    onChange={value => inputChage(value)}
                    value={value.education[index].degree}
                />
            </div>
            <div className='inputs-container'>
                <Calendar
                    label='Started'
                    name={`education.${index}.startDate`}
                    placeholder='Month, year'
                    halfWidth
                    value={value.education[index].startDate}
                    onChange={value => select(`education.${index}.startDate`, value)}
                />
                <Calendar
                    label='Finished'
                    name={`education.${index}.endDate`}
                    placeholder='Month, year'
                    halfWidth
                    value={value.education[index].endDate}
                    onChange={value => select(`education.${index}.endDate`, value)}
                />
            </div>
        </div>
    )
}

export default Education;