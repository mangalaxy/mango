import React from 'react';
import {town} from "../../../assets/icons";
import colors from "../../../constants/colors";
import SimpleSelect from "../../../components/inputs/Select/SimpleSelect/SimpleSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];


function TalentExpectations(props) {
    const {user, edit} = props;

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={town(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Expectations</div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Type of company:</div>
                {
                    edit ?
                        <SimpleSelect
                            options={mockOptions}
                            multi={true}
                            directionTable
                        />
                        :
                        <div className='section-row__value'>{user.expectations}</div>
                }

            </div>
        </div>
    )
}

export default TalentExpectations;