import React from 'react';
import colors from "../../../constants/colors";
import {brain} from "../../../assets/icons";
import DropDownSelect from "../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import SimpleSelect from "../../../components/inputs/Select/SimpleSelect/SimpleSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];

function TalentRoles(props) {
    const {user, edit} = props;
    const {experienceAndRoles} = user;
    const {jobRole, specialization, total} = experienceAndRoles;
    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={brain(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Experience & Roles</div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Job role</div>
                {
                    edit ?
                        <DropDownSelect
                            name='role'
                            options={mockOptions}
                            multi={false}
                        />
                        :
                        <div className='section-row__value'>{jobRole}</div>

                }

            </div>
            <div className='section-row'>
                <div className='section-row__title'>Specializations</div>
                {
                    edit ?
                        <SimpleSelect
                            options={mockOptions}
                            multi={true}
                            directionTable
                        />
                        :
                        <div className='section-row__value'>{specialization}</div>
                }
            </div>
        </div>
    )
}

export default TalentRoles;