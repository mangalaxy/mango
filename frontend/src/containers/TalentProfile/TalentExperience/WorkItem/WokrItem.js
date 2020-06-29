import React from 'react';
import TextInput from '../../../../components/inputs/TextInput/TextInput';
import TextArea from '../../../../components/inputs/TextArea/TextArea';
import Calendar from "../../../../components/inputs/Calendar/Calendar";
import moment from 'moment';

const WorkItem = (props) => {
    const {work = {}, edit, inputChange, onSelect, user, index} = props;
    const {company, position, description, startDate, endDate} = work;
    const deleteWork = (e) => {
        const wokrList = user.experience.filter((work, i) => i !== index);
        onSelect(onSelect('experience', wokrList));
    };

    const getDate = (date) => {
      if (date) {
        return moment(date, 'YYYY-MM-DD').toDate();
      }

      return '';
    };

    return (
        <div className='talent-worklist__item'>
            {edit && <div onClick={deleteWork} className='delete-row'>&#215;</div>}
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            name={`experience.${index}.company`}
                            type='text'
                            defaultValue={user.experience[index].company || ''}
                            onChange={inputChange}
                        />
                        :
                        <div className='section-row__value'>{company}</div>
                }
            </div>
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            name={`experience.${index}.position`}
                            type='text'
                            defaultValue={user.experience[index].position || ''}
                            onChange={inputChange}
                        />
                        :
                        <div className='section-row__value'>{position}</div>
                }

            </div>
            <div className='section-block'>
                <div className='section-row'>
                    <div className='section-row__title'>Started</div>
                    {
                        edit ?
                            <Calendar
                                name={`experience.${index}.startDate`}
                                placeholder='Month, year'
                                halfWidth
                                selected={getDate(user.experience[index].startDate)}
                                onChange={value => onSelect(`experience.${index}.startDate`, value)}
                            />
                            :
                            <div className='section-row__value'>{startDate}</div>
                    }

                </div>
                <div className='section-row'>
                    <div className='section-row__title'>Finished</div>
                    {
                        edit ?
                            <Calendar
                                name={`experience.${index}.endDate`}
                                placeholder='Month, year'
                                halfWidth
                                selected={getDate(user.experience[index].endDate)}
                                onChange={value => onSelect(`experience.${index}.endDate`, value)}
                            />
                            :
                            <div className='section-row__value'>{endDate}</div>
                    }

                </div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Description</div>
                {
                    edit ?
                        <TextArea
                            name={`experience.${index}.description`}
                            type='text'
                            defaultValue={user.experience[index].description || ''}
                            height={80}
                            onChange={inputChange}
                        />
                        :
                        <div className='section-row__value'>{description}</div>
                }

            </div>
        </div>
    )
}

export default WorkItem;