import React from 'react';
import colors from "../../../constants/colors";
import {location} from "../../../assets/icons";
import DropDownSelect from "../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];

function TalentLocation(props) {
    const {user, edit} = props;
    const {country, city} = user.talent.location;

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={location(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Location</div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Want to work in the area:</div>
                {
                    edit ?
                        <DropDownSelect
                            name='role'
                            options={mockOptions}
                            multi={false}
                        />
                        :
                        <div className='section-row__value'>{country}, {city}</div>

                }

            </div>
        </div>
    )
}

export default TalentLocation;