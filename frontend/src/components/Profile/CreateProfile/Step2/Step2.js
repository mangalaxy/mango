import React from 'react';
import SimpleSelect from "../../../inputs/Select/SimpleSelect/SimpleSelect";
import DropDownSelect from "../../../inputs/Select/DropDownSelect/DropDownSelect";
import FormButton from "../../../Buttons/FormButton/FormButton";
import TextInput from "../../../inputs/TextInput/TextInput";

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
    const {hidden, next, prev, profile, inputChange, onSelect} = props;

    return (
        <div className='profile-form__item' hidden={hidden}>
            <div className='profile-form__container'>
                <div className='profile-form__column-left'>
                    <h2 className='profile-form__title'>expectations</h2>
                    <SimpleSelect
                        label='What are your expectations in the new company?'
                        name='expectations'
                        options={mockExpectations}
                        multi={false}
                        className='react-dropdown-select-dropdown--block'
                        directionTable={true}
                        onChange={value => onSelect('expectations', value)}
                        value={profile.expectations}
                    />
                    <h2 className='profile-form__title'>type of company</h2>
                    <SimpleSelect
                        label='What type of company suits you?'
                        name='typeOfCompany'
                        options={mockCompaniTypes}
                        multi={false}
                        onChange={value => onSelect('typeOfCompany', value)}
                        value={profile.typeOfCompany}
                    />
                </div>
                <div className='profile-form__column-left'>
                    <h2 className='profile-form__title'>Salary</h2>
                    <TextInput
                        label='What are your base salary expectations?'
                        name='salary'
                        type='number'
                        onChange={inputChange}
                        value={profile.salary}
                    />
                    <h2 className='profile-form__title'>Industry</h2>
                    <DropDownSelect
                        label='What industry would you like to work in?'
                        name='industries'
                        options={mockIndustries}
                        multi={true}
                        placeholder='Industry'
                        onChange={value => onSelect('industries', value)}
                        value={profile.industries}
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
                        onClick={next}
                    />
                </div>
            </div>
        </div>
    )
}

export default Step2;