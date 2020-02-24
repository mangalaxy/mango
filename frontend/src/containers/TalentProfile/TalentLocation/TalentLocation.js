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
    const {user, edit, onSelect} = props;
    const {country, city} = user.prefferedLocation;

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
                        <div>
                            <DropDownSelect
                                name='prefferedLocation.country'
                                options={mockOptions}
                                multi={false}
                                onChange={value => onSelect('prefferedLocation.country', value)}
                                placeholder='country'
                            />
                            <DropDownSelect
                                name='prefferedLocation.city'
                                options={mockOptions}
                                multi={false}
                                onChange={value => onSelect('prefferedLocation.city', value)}
                                placeholder='city'
                            />
                        </div>
                        :
                        <div className='section-row__value'>{country}, {city}</div>

                }

            </div>
        </div>
    )
}

export default TalentLocation;