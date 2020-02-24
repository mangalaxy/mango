import React, {Fragment} from 'react';
import SimpleSelect from "../../../inputs/Select/SimpleSelect/SimpleSelect";
import DropDownSelect from "../../../inputs/Select/DropDownSelect/DropDownSelect";
import FormButton from "../../../Buttons/FormButton/FormButton";
import TextInput from "../../../inputs/TextInput/TextInput";
import {useFormik} from 'formik';

const mockExpectations = [
    {label: 'Large project', value: 'Large project'},
    {label: 'Applying new technology', value: 'Applying new technology'},
    {label: 'Long-term contract', value: 'Long-term contract'},
];

const mockCompaniTypes = [
    {label: 'Outsourcing', value: 'Outsourcing'},
    {label: 'Start-up', value: 'Start-up'},
    {label: 'Product company', value: 'Product company'},
    {label: 'Social project', value: 'Social project'},
];

const mockIndustries = [
    {label: 'Industry1', value: 'Industry1'},
    {label: 'Industry2', value: 'Industry2'},
    {label: 'Industry3', value: 'Industry3'},
    {label: 'Industry4', value: 'Industry4'},
    {label: 'Industry5', value: 'Industry5'},
];

function Step2(props) {
    const {hidden, next, prev, onSubmitStep} = props;
    const formik = useFormik({
        initialValues: {
            typeOfCompany: '',
            expectations: [],
            salary: '',
            industries: []

        },
        onSubmit: values => {
            onSubmitStep(values);
        },
    });

    const clickButton = () => {
        formik.handleSubmit()
        next();
    }

    return (
        <div className='profile-form__item' hidden={hidden}>
            <form className='profile-form__container'>
                <div className='profile-form__column-left'>
                    <h2 className='profile-form__title'>expectations</h2>
                    <SimpleSelect
                        label='What are your expectations in the new company?'
                        name='expectations'
                        options={mockExpectations}
                        multi={false}
                        className='react-dropdown-select-dropdown--block'
                        directionTable={true}
                        onChange={value => formik.setFieldValue('expectations', value)}
                        value={formik.values.expectations}
                    />
                    <h2 className='profile-form__title'>type of company</h2>
                    <SimpleSelect
                        label='What type of company suits you?'
                        name='typeOfCompany'
                        options={mockCompaniTypes}
                        multi={false}
                        onChange={value => formik.setFieldValue('typeOfCompany', value)}
                        value={formik.values.typeOfCompany}
                    />
                </div>
                <div className='profile-form__column-left'>
                    <h2 className='profile-form__title'>Salary</h2>
                    <TextInput
                        label='What are your base salary expectations?'
                        name='salary'
                        type='number'
                        onChange={formik.handleChange}
                        value={formik.values.salary}
                    />
                    <h2 className='profile-form__title'>Industry</h2>
                    <DropDownSelect
                        label='What industry would you like to work in?'
                        name='industries'
                        options={mockIndustries}
                        multi={true}
                        placeholder='Industry'
                        onChange={value => formik.setFieldValue('industries', value)}
                        value={formik.values.industries}
                    />
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
            </form>
        </div>
    )
}

export default Step2;