import React from 'react'
import Calendar from "../../../../inputs/Calendar/Calendar";
import TextInput from "../../../../inputs/TextInput/TextInput";

function Education() {
    return (
        <div className='profile-form__row'>
            <div className='inputs-container'>
                <TextInput
                    name='institute'
                    type='text'
                    placeholder='Institution'
                    halfWidth
                />
                <TextInput
                    name='specialization'
                    type='text'
                    placeholder='Specialization'
                    halfWidth
                />
                <TextInput
                    name='degree'
                    type='text'
                    placeholder='Degree'
                    halfWidth
                />
            </div>
            <div className='inputs-container'>
                <Calendar
                    label='Started'
                    name='uducationStart'
                    placeholder='Month, year'
                    halfWidth
                />
                <Calendar
                    label='Finished'
                    name='uducationFinished'
                    placeholder='Month, year'
                    halfWidth
                />
            </div>
        </div>
    )
}

export default Education;