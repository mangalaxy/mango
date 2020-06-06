import React from 'react'
import Calendar from "../../../../inputs/Calendar/Calendar";
import TextArea from "../../../../inputs/TextArea/TextArea";
import TextInput from "../../../../inputs/TextInput/TextInput";

function Experience(props) {
    const {index, inputChage, select, value} = props;

    return (
        <div className='profile-form__row'>
            <TextInput
                name={`experience.${index}.company`}
                type='text'
                placeholder='Company'
                onChange={value => inputChage(value)}
                value={value.experience[index].company}
            />
            <TextInput
                name={`experience.${index}.position`}
                type='text'
                placeholder='Position'
                onChange={value => inputChage(value)}
                value={value.experience[index].position}
            />
            <div className='inputs-container'>
                <Calendar
                    label='Started'
                    name={`experience.${index}.startDate`}
                    placeholder='Month, year'
                    halfWidth
                    value={value.experience[index].startDate}
                    onChange={value => select(`experience.${index}.startDate`, value)}
                />
                <Calendar
                    label='Finished'
                    name={`experience.${index}.endDate`}
                    name='finishedWork'
                    placeholder='Month, year'
                    halfWidth
                    value={value.experience[index].endDate}
                    onChange={value => select(`experience.${index}.endDate`, value)}
                />
            </div>
            <TextArea
                label='Description'
                name={`experience.${index}.description`}
                type='text'
                placeholder=''
                className='text-input__field--big-height'
                onChange={value => inputChage(value)}
                value={value.experience[index].description}
            />
        </div>
    )
}

export default Experience;