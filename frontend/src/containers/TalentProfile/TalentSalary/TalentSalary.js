import React from 'react';
import colors from "../../../constants/colors";
import {salary} from "../../../assets/icons";
import TextInput from '../../../components/inputs/TextInput/TextInput';
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

function TalentSalary(props) {
    const {user, edit} = props;

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={salary(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Salary</div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Expectations:</div>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={user.salary}
                        />
                        :
                        <div className='section-row__value'>{user.salary}</div>
                }

            </div>
        </div>
    )
}

export default TalentSalary;