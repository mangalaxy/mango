import React, {useState, useEffect} from 'react';
import Experience from './Experience/Experience';
import Education from './Education/Education';
import Languages from './Languages/Languages';
import FormButton from "../../../Buttons/FormButton/FormButton";
import {useFormik} from 'formik';

function Step4(props) {
    const {hidden, prev, next, onSubmitStep, experienceList, educationList, languagesList} = props;
    const [experienceCount, setExperienceCount] = useState(1);
    const [educationCount, setEducationCount] = useState(1);
    const [languagesCount, setLanguagesCount] = useState(1);

    const formik = useFormik({
        initialValues: {
            experience: [{}],
            education: [{}],
            languages: [{}]
        },
        onSubmit: values => {
            onSubmitStep(values);
        },
    });

    const clickButton = () => {
        formik.handleSubmit()
        next();
    }

    const experiences = Array.apply(null, {length: experienceCount}).map((item, index) => <Experience index={index} inputChage={formik.handleChange} select={formik.setFieldValue} value={formik.values} key={index}/>);
    const educations = Array.apply(null, {length: educationCount}).map((item, index) => <Education index={index} inputChage={formik.handleChange} select={formik.setFieldValue} value={formik.values} key={index}/>);
    const languages = Array.apply(null, {length: languagesCount}).map((item, index) => <Languages index={index} inputChage={formik.handleChange} select={formik.setFieldValue} value={formik.values} key={index}/>);

    return (
        <div className='profile-form__item' hidden={hidden}>
            <div className='profile-form__container'>
                <div className='profile-form__column-left'>
                    <div className='profile-form__title-container'>
                        <h2 className='profile-form__title'>Experience</h2>
                        <div
                            className='profile-form__add-icon'
                            onClick={() => {
                                setExperienceCount(experienceCount + 1);
                                formik.setFieldValue('experience', formik.values.experience.concat([{}]))
                            }}
                        >
                            +
                        </div>
                    </div>
                    {experiences}
                </div>
                <div className='profile-form__column-right'>
                    <div className='profile-form__title-container'>
                        <h2 className='profile-form__title'>Education</h2>
                        <div
                            className='profile-form__add-icon'
                            onClick={() => {
                                setEducationCount(educationCount + 1);
                                formik.setFieldValue('education', formik.values.education.concat([{}]));
                            }}
                        >+</div>
                    </div>
                    {educations}
                    <div className='profile-form__title-container'>
                        <h2 className='profile-form__title profile-form__title--margin-bottom-none'>Languages</h2>
                        <div
                            className='profile-form__add-icon profile-form__add-icon--margin-top'
                            onClick={() => {
                                setLanguagesCount(languagesCount + 1);
                                formik.setFieldValue('languages', formik.values.languages.concat([{}]));
                            }}
                        >+</div>
                    </div>
                    {languages}
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
                        onClick={clickButton}
                    />
                </div>
            </div>
        </div>
    )
}

export default Step4;