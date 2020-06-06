import React from 'react';
import colors from "../../../constants/colors";
import {links} from "../../../assets/icons";
import TextInput from '../../../components/inputs/TextInput/TextInput';
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

function TalentLinks(props) {
    const {user, edit, onСhange} = props;
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
                            name='links.portfolio'
                            type='text'
                            defaultValue={user.links.portfolio}
                            onChange={onСhange}
                            value={user.links.portfolio}
                        />
                        :
                        <div className='section-row__value'>{user.links.portfolio}</div>
                }

            </div>
            <div className='section-row'>
                <div className='section-row__title'>Blog</div>
                {
                    edit ?
                        <TextInput
                            name='links.blog'
                            type='text'
                            defaultValue={user.links.blog}
                            onChange={onСhange}
                            value={user.links.blog}
                        />
                        :
                        <div className='section-row__value'>{user.links.blog}</div>
                }

            </div>
        </div>
    )
}

export default TalentLinks;