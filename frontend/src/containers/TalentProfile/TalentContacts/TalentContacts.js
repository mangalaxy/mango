import React, {Fragment} from 'react';
import TextInput from '../../../components/inputs/TextInput/TextInput';
import colors from "../../../constants/colors";
import {email} from "../../../assets/icons";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

function TalentContacts(props) {
    const {user, edit, onСhange} = props;
    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={email(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>contact</div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Email</div>
                {
                    edit ?
                        <TextInput
                            name='talent.email'
                            type='email'
                            defaultValue={user.talent.email}
                            onChange={onСhange}
                            value={user.talent.email}
                        />
                        :
                        <div className='section-row__value'>{user.talent.email}</div>
                }

            </div>
            <div className='section-row'>
                <div className='section-row__title'>Phone</div>
                {
                    edit ?
                        <TextInput
                            name='phone'
                            type='text'
                            defaultValue={user.phone}
                            onChange={onСhange}
                            value={user.phone}
                        />
                        :
                        <div className='section-row__value'>{user.phone}</div>
                }

            </div>
        </div>
    )
}

export default TalentContacts;