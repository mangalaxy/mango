import React from 'react';
import colors from "../../../constants/colors";
import {certificates} from "../../../assets/icons";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import TextArea from '../../../components/inputs/TextArea/TextArea';

function TalentSertificates(props) {
    const {user, edit, onСhange} = props;
    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={certificates(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>licenses and certificates</div>
            </div>
            <div className='section-row'>
                {
                    edit ?
                        <TextArea
                            name='certificates'
                            type='text'
                            defaultValue={user.certificates}
                            height={80}
                            onChange={onСhange}
                            value={user.certificates}
                        />
                        :
                        <div className='section-row__value'>{user.certificates}</div>
                }
            </div>
        </div>
    )
}

export default TalentSertificates;