import React from 'react';
import {town} from "../../../assets/icons";
import colors from "../../../constants/colors";
import SimpleSelect from "../../../components/inputs/Select/SimpleSelect/SimpleSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Start-up company', value: 'Start-up company'},
    {label: 'Outsourcing company', value: 'Outsourcing company'},
    {label: 'Outsourcing company', value: 'Product company'},
];


function TalentExpectations(props) {
    const {user, edit, onСhange, onSelect} = props;
    const values = mockOptions.filter(option => user.expectations.includes(option.value));

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
                            name='expectations'
                            options={mockOptions}
                            multi={true}
                            directionTable
                            values={values}
                            onChange={value => onSelect('expectations', value)}
                        />
                        :
                        <div className='section-row__value'>{user.expectations.join(', ')}</div>
                }

            </div>
        </div>
    )
}

export default TalentExpectations;