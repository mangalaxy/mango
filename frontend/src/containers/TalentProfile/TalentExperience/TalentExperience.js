import React from 'react';
import {portfolio} from "../../../assets/icons";
import colors from "../../../constants/colors";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import WorkItem from './WorkItem/WokrItem';

function TalentExperience(props) {
    const {user, edit, onСhange, onSelect} = props;
    const {experience} = user;
    const worksList = experience.map((item, index) => {
        return (
            <WorkItem
                work={item}
                key={item.id}
                edit={edit}
                inputChange={onСhange}
                onSelect={onSelect}
                index={index}
                user={user}
            />
        )
    })

    const addNewWork = () => {
        onSelect('experience', experience.concat([{}]));
    };

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={portfolio(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>
                    Work experience
                    {edit &&
                    <span onClick={addNewWork}
                          className='section-title__text--clicked'>+</span>
                    }
                </div>
            </div>
            <div className='talent-worklist'>
                {worksList}
            </div>
        </div>
    )
}

export default TalentExperience;