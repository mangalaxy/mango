import React, {Fragment} from 'react';
import TextInput from "../../../inputs/TextInput/TextInput";
import DropDownSelect from "../../../inputs/Select/DropDownSelect/DropDownSelect";
import FormButton from "../../../Buttons/FormButton/FormButton";

const mockYear = [
    {label: '1990', value: '1990'},
    {label: '1991', value: '1991'},
    {label: '1992', value: '1992'},
];

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

function Step4(props) {
    const {hidden, prev, next} = props;
    return (
        <div className='profile-form__item' hidden={hidden}>
            <div className='profile-form__container'>
                <div className='profile-form__column-left'>
                    <h2 className='profile-form__title'>Experience</h2>
                    <TextInput
                        name='position'
                        type='text'
                        placeholder='Company'
                    />
                    <TextInput
                        name='position'
                        type='text'
                        placeholder='Position'
                    />
                    <div className='inputs-container'>
                        <DropDownSelect
                            label='Started'
                            name='Started'
                            options={mockYear}
                            multi={true}
                            placeholder='Month, year'
                            halfWidth
                        />
                        <DropDownSelect
                            label='Finished'
                            name='Finished'
                            options={mockYear}
                            multi={true}
                            placeholder='Month, year'
                            halfWidth
                        />
                    </div>
                    <TextInput
                        label='Description'
                        name='position'
                        type='text'
                        placeholder=''
                        className='text-input__field--big-height'
                    />
                </div>
                <div className='profile-form__column-right'>
                    <h2 className='profile-form__title'>Education</h2>
                    <div className='inputs-container'>
                        <TextInput
                            name='position'
                            type='text'
                            placeholder='Institution'
                            halfWidth
                        />
                        <TextInput
                            name='position'
                            type='text'
                            placeholder='Specialization'
                            halfWidth
                        />
                        <TextInput
                            name='position'
                            type='text'
                            placeholder='Degree'
                            halfWidth
                        />
                    </div>
                    <div className='inputs-container'>
                        <DropDownSelect
                            label='Started'
                            name='Started'
                            options={mockYear}
                            multi={true}
                            placeholder='Month, year'
                            halfWidth
                        />
                        <DropDownSelect
                            label='Finished'
                            name='Finished'
                            options={mockYear}
                            multi={true}
                            placeholder='Month, year'
                            halfWidth
                        />
                    </div>
                    <h2 className='profile-form__title profile-form__title--margin-bottom-none'>Languages</h2>
                    <div className='inputs-container'>
                        <DropDownSelect
                            label='Started'
                            name='Started'
                            options={mockLenguage}
                            multi={true}
                            placeholder='Month, year'
                            halfWidth
                        />
                        <DropDownSelect
                            label='Finished'
                            name='Finished'
                            options={mockLevels}
                            multi={true}
                            placeholder='Month, year'
                            halfWidth
                        />
                    </div>
                </div>
                <div className='buttons-container'>
                    <FormButton
                        text='Previous'
                        className='form-button--white'
                        onClick={prev}
                    />
                    <FormButton
                        text='Next'
                        className='form-button--red'
                        onClick={next}
                    />
                </div>
            </div>
        </div>
    )
}

export default Step4;