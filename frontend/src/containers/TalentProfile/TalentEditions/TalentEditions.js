import React from 'react';
import colors from "../../../constants/colors";
import {edit} from "../../../assets/icons";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import TextArea from '../../../components/inputs/TextArea/TextArea';

function TalentEditions(props) {
    const {user, isEdit} = props;
    const {headline, objectives} = user;

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={edit(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Additional sections</div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Headline</div>
                {
                    isEdit ?
                        <TextArea
                            type='text'
                            height={80}
                            placeholder='Owner of UX product'
                        />
                        :
                        <div className='section-row__value'>{headline}</div>

                }
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Objectives</div>
                {
                    isEdit ?
                        <TextArea
                            type='text'
                            height={80}
                            placeholder='Shiping 15+ successful app'
                        />
                        :
                        <div className='section-row__value'>{objectives}</div>
                }

            </div>
        </div>
    )
}

export default TalentEditions;