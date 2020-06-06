import React from 'react';
import {education} from "../../../../assets/icons";
import TextInput from '../../../../components/inputs/TextInput/TextInput';
import DropDownSelect from "../../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import Calendar from "../../../../components/inputs/Calendar/Calendar";
import moment from 'moment';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
    {label: 'Master', value: 'Master'},
];

const EducationItem = (props) => {
    const {edit, inputChange, onSelect, user, index} = props;
    const deleteEducation = (e) => {
        const educationList = user.education.filter((item, i) => i !== index);
        onSelect(onSelect('education', educationList));
    };

    const degree = mockOptions.filter(option => user.education[index].degree === option.value);
    
    const getDate = (date) => {
        if (date) {
            return moment(date, 'YYYY-MM-DD').toDate();
        }

        return '';
    };

    return (
        <div className='talent-educations__item'>
            {edit && <div onClick={deleteEducation} className='delete-row'>&#215;</div>}
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            name={`education.${index}.university`}
                            type='text'
                            defaultValue={user.education[index].university || ''}
                            onChange={inputChange}
                        />
                        :
                        <div className='section-row__value'>{user.education[index].university}</div>
                }

            </div>
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            name={`education.${index}.speciality`}
                            type='text'
                            defaultValue={user.education[index].speciality || ''}
                            onChange={inputChange}
                        />
                        :
                        <div className='section-row__value'>{user.education[index].speciality}</div>
                }

            </div>
            <div className={`section-row ${edit && 'section-row--margin-top'}`}>
                {
                    edit ?
                        <DropDownSelect
                            name={`education.${index}.degree`}
                            options={mockOptions}
                            multi={false}
                            halfWidth
                            onChange={value => onSelect(`education.${index}.degree`, value)}
                            values={degree}
                        />
                        :
                        <div className='section-row__value'>{user.education[index].degree}</div>
                }

            </div>
            <div className='section-block'>
                <div className='section-row'>
                    <div className='section-row__title'>Started</div>
                    {
                        edit ?
                            <Calendar
                                name={`experience.${index}.start`}
                                placeholder='Month, year'
                                halfWidth
                                selected={getDate(user.education[index].start)}
                                onChange={value => onSelect(`education.${index}.start`, value)}
                            />
                            :
                            <div className='section-row__value'>{user.education[index].start}</div>
                    }

                </div>
                <div className='section-row'>
                    <div className='section-row__title'>Finished</div>
                    {
                        edit ?
                            <Calendar
                                name={`experience.${index}.end`}
                                placeholder='Month, year'
                                halfWidth
                                selected={getDate(user.education[index].end)}
                                onChange={value => onSelect(`education.${index}.end`, value)}
                            />
                            :
                            <div className='section-row__value'>{user.education[index].end}</div>
                    }
                </div>
            </div>
        </div>
    )
}

export default EducationItem;
