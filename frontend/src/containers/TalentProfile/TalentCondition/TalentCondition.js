import React from 'react';
import colors from "../../../constants/colors";
import {clock} from "../../../assets/icons";
import DropDownSelect from "../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
    {label: 'Full-time', value: 'Full-time'},
];


function TalentCondition(props) {
    const {user, edit, onSelect} = props;
    const type = mockOptions.filter(option => user.employmentType === option.value);
    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={clock(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Condition</div>
            </div>
            <div className='section-row'>
                {
                    edit ?
                        <div className='section-row__title'>Choose type of employment</div>
                        :
                        <div className='section-row__title'>Type of employment</div>
                }

                {
                    edit ?
                        <DropDownSelect
                            name='employmentType'
                            options={mockOptions}
                            multi={false}
                            values={type}
                            onChange={value => onSelect('employmentType', value)}
                        />
                        :
                        <div className='section-row__value'>{user.employmentType}</div>
                }

            </div>
        </div>
    )
}

export default TalentCondition;