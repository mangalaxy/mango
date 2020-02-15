import React from 'react'
import Calendar from "../../../../inputs/Calendar/Calendar";
import TextArea from "../../../../inputs/TextArea/TextArea";
import TextInput from "../../../../inputs/TextInput/TextInput";

function Experience() {
    return (
        <div className='profile-form__row'>
            <TextInput
                name='company'
                type='text'
                placeholder='Company'
            />
            <TextInput
                name='companyPosition'
                type='text'
                placeholder='Position'
            />
            <div className='inputs-container'>
                <Calendar
                    label='Started'
                    name='startedWork'
                    placeholder='Month, year'
                    halfWidth
                />
                <Calendar
                    label='Finished'
                    name='finishedWork'
                    placeholder='Month, year'
                    halfWidth
                />
            </div>
            <TextArea
                label='Description'
                name='description'
                type='text'
                placeholder=''
                className='text-input__field--big-height'
            />
        </div>
    )
}

export default Experience;