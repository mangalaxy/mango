import React from 'react';
import {education} from "../../../assets/icons";
import colors from "../../../constants/colors";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import EducationItem from './EducationItem/EducationItem';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];

function TalentEducation(props) {
    const {user, edit, onСhange, onSelect} = props;
    const educationsList = user.education.map((item, index) => {
        return (
            <EducationItem
                education={item}
                key={index}
                edit={edit}
                inputChange={onСhange}
                onSelect={onSelect}
                index={index}
                user={user}
            />
            )
    });

    const addNewEducation = () => {
        const {education} = user;
        onSelect('education', education.concat([{}]));
    };

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={education(colors.COLOR_GREY)}/>
                <div className='section-title__text'>
                    Education
                    {edit && <span onClick={addNewEducation} className='section-title__text--clicked'>+</span>}
                </div>
            </div>
            <div className='talent-educations'>
                {educationsList}
            </div>
        </div>
    )
}

export default TalentEducation;