import React from 'react';
import './CreateInterview.scss'
import {useFormik} from 'formik';
import TextInput from "../../inputs/TextInput/TextInput";
import TextArea from "../../inputs/TextArea/TextArea";
import DropDownSelect from "../../inputs/Select/DropDownSelect/DropDownSelect";
import Calendar from "../../inputs/Calendar/Calendar";
import CheckBox from '../../inputs/CheckBox/CheckBox';
import FormButton from "../../Buttons/FormButton/FormButton";

const mockTimeZones = [
    {label: '(GMT+2:00) Helsinki, Istanbul', value: '(GMT+2:00) Helsinki, Istanbul'},
    {label: '(GMT+2:00) Ukraine, Kyiv', value: '(GMT+2:00) Ukraine, Kyiv'},
    {label: '(GMT+2:00) England, London', value: '(GMT+2:00) England, London'},
];

const mockDurations = [
    {label: '1 hours', value: '1 hours'},
    {label: '2 hours', value: '2 hours'},
    {label: '3 hours', value: '3 hours'},

];

const mockFormats = [
    {label: 'Phone', value: 'Phone'},
    {label: 'Video Chat', value: 'Video Chat'},
    {label: 'Office', value: 'Office'},
];

const mockCities = [
    {label: 'Kyiv', value: 'Kyiv'},
    {label: 'Lviv', value: 'Lviv'},
    {label: 'Odessa', value: 'Odessa'},
];

function CreateInterview(props) {
    const formik = useFormik({
        initialValues: {
        },
        onSubmit: values => {
            console.log(values);
        },
    });

    return (
        <div className='create-interview'>
            <div className='create-interview__container'>
                <h1 className='create-interview__title'>Schedule Interview</h1>
                <h3 className='create-interview__talent_name'>with Arthur van Hoff</h3>
                <form className='create-interview__form'>
                    <div className='create-interview__row create-interview__row--margin-bottom'>
                        <div className='create-interview__row-title'>Interviewer</div>
                        <div className='create-interview__row-input'>
                            <TextInput
                                name='interviewer'
                                type='text'
                                onChange={formik.handleChange}
                                value={formik.values.interviewer}
                                className='create-interview__input'
                            />
                        </div>
                    </div>
                    <div className='create-interview__row'>
                        <div className='create-interview__row-title'>Time Zone</div>
                        <div className='create-interview__row-input'>
                            <DropDownSelect
                                name='timeZone'
                                options={mockTimeZones}
                                multi={false}
                                type='text'
                                onChange={value => formik.setFieldValue('timeZone', value)}
                                placeholder=''
                            />
                        </div>
                    </div>
                    <div className='create-interview__row'>
                        <div className='create-interview__row-title'>Duration</div>
                        <div className='create-interview__row-input'>
                            <DropDownSelect
                                name='duration'
                                options={mockDurations}
                                multi={false}
                                type='text'
                                onChange={value => formik.setFieldValue('duration', value)}
                                placeholder=''
                            />
                        </div>
                    </div>
                    <div className='create-interview__row create-interview__row--margin-top'>
                        <div className='create-interview__row-title'>Date and Time</div>
                        <div className='create-interview__row-input'>
                            <Calendar
                                name='date'
                                onChange={value => formik.setFieldValue('date', value)}
                                className='create-interview__input'
                                width={100}
                                time
                            />
                        </div>
                    </div>
                    <div className='create-interview__row create-interview__row--margin-bottom'>
                        <div className='create-interview__row-title'></div>
                        <div className='create-interview__row-input'>
                            <CheckBox
                                name='addToCalendar'
                                label='Add to calendar'
                                onChange={value => formik.setFieldValue('addToCalendar', value)}
                            />
                        </div>
                    </div>
                    <div className='create-interview__row'>
                        <div className='create-interview__row-title'>Format</div>
                        <div className='create-interview__row-input'>
                            <DropDownSelect
                                name='format'
                                options={mockFormats}
                                multi={false}
                                type='text'
                                onChange={value => formik.setFieldValue('format', value)}
                                placeholder=''
                            />
                        </div>
                    </div>
                    <div className='create-interview__row'>
                        <div className='create-interview__row-title'>Location</div>
                        <div className='create-interview__row-input'>
                            <DropDownSelect
                                name='location'
                                options={mockCities}
                                multi={false}
                                type='text'
                                onChange={value => formik.setFieldValue('location', value)}
                                placeholder=''
                            />
                        </div>
                    </div>
                    <div className='create-interview__row create-interview__row--textarea'>
                        <div className='create-interview__row-title create-interview__row-title--margin-top'>Comment</div>
                        <div className='create-interview__row-input'>
                             <TextArea
                                 name='comment'
                                 type='text'
                                 onChange={formik.handleChange}
                                 height={150}
                             />
                        </div>
                    </div>
                    <div className='create-interview__submit'>
                        <FormButton
                            text='schedule'
                            className='form-button--red'
                            onClick={formik.handleSubmit}
                        />
                    </div>
                </form>
            </div>
        </div>
    )
}

export default CreateInterview;