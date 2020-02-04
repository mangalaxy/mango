import React, {Fragment} from 'react';
import TextInput from '../../../components/inputs/TextInput/TextInput';
import colors from "../../../constants/colors";
import {email} from "../../../assets/icons";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

function TalentContacts(props) {
    const {user, edit} = props;
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
                            type='email'
                            defaultValue={user.email}
                        />
                        :
                        <div className='section-row__value'>{user.email}</div>
                }

            </div>
            <div className='section-row'>
                <div className='section-row__title'>Phone</div>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={user.phone}
                        />
                        :
                        <div className='section-row__value'>{user.phone}</div>
                }

            </div>
        </div>
    )
}

export default TalentContacts;