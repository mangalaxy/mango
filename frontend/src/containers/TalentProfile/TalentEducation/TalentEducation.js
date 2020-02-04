import React, {useState} from 'react';
import {education} from "../../../assets/icons";
import colors from "../../../constants/colors";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import TextInput from '../../../components/inputs/TextInput/TextInput';
import DropDownSelect from "../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import Calendar from "../../../components/inputs/Calendar/Calendar";

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];

function TalentEducation(props) {
    const {user, edit} = props;
    const [educationsCount, setEducationsCount] = useState(0);
    const educationsList = user.userEducations.map(item => <UserEducationsItem education={item} key={item.id} edit={edit} />)
    const addedEducationsList = Array.apply(null, {length: educationsCount}).map((item, i) => <UserEducationsItem key={i} edit={edit} />);

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={education(colors.COLOR_GREY)}/>
                <div className='section-title__text'>
                    Education
                    {edit && <span onClick={() => setEducationsCount(educationsCount+1)} className='section-title__text--clicked'>+</span>}
                </div>
            </div>
            <div className='talent-educations'>
                {educationsList}
                {addedEducationsList}
            </div>
        </div>
    )
}

const UserEducationsItem = (props) => {
    const {education = {}, edit} = props;
    const deleteEducation = (e) => {
        e.target.parentNode.remove();
    };

    return (
        <div className='talent-educations__item'>
            {edit && <div onClick={deleteEducation} className='delete-row'>&#215;</div>}
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={education.university || ''}
                        />
                        :
                        <div className='section-row__value'>{education.university}</div>
                }

            </div>
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={education.educationSpeciality || ''}
                        />
                        :
                        <div className='section-row__value'>{education.educationSpeciality}</div>
                }

            </div>
            <div className={`section-row ${edit && 'section-row--margin-top'}`}>
                {
                    edit ?
                        <DropDownSelect
                            name='role'
                            options={mockOptions}
                            multi={false}
                            halfWidth
                        />
                        :
                        <div className='section-row__value'>{education.degree}</div>
                }

            </div>
            <div className='section-block'>
                <div className='section-row'>
                    <div className='section-row__title'>Started</div>
                    {
                        edit ?
                            <Calendar
                                name='Started'
                                placeholder='Month, year'
                                halfWidth
                            />
                            :
                            <div className='section-row__value'>{education.start}</div>
                    }

                </div>
                <div className='section-row'>
                    <div className='section-row__title'>Finished</div>
                    {
                        edit ?
                            <Calendar
                                name='Started'
                                placeholder='Month, year'
                                halfWidth
                            />
                            :
                            <div className='section-row__value'>{education.end}</div>
                    }
                </div>
            </div>
        </div>
    )
}

export default TalentEducation;