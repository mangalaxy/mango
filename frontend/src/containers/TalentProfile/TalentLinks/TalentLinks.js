import React, {Fragment} from 'react';
import colors from "../../../constants/colors";
import {links} from "../../../assets/icons";
import TextInput from '../../../components/inputs/TextInput/TextInput';
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

function TalentLinks(props) {
    const {user, edit} = props;
    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={links(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>links</div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Portfolio</div>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={user.portfolio}
                        />
                        :
                        <div className='section-row__value'>{user.portfolio}</div>
                }

            </div>
            <div className='section-row'>
                <div className='section-row__title'>Blog</div>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={user.blog}
                        />
                        :
                        <div className='section-row__value'>{user.blog}</div>
                }

            </div>
        </div>
    )
}

export default TalentLinks;