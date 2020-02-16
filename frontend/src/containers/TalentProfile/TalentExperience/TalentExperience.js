import React, {useState} from 'react';
import {portfolio} from "../../../assets/icons";
import colors from "../../../constants/colors";
import TextInput from '../../../components/inputs/TextInput/TextInput';
import TextArea from '../../../components/inputs/TextArea/TextArea';
import Calendar from "../../../components/inputs/Calendar/Calendar";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

function TalentExperience(props) {
    const {user, edit} = props;
    const {userWorkExperience} = user;
    const [worksCount, setWorksCount] = useState(0);
    const worksList = userWorkExperience.map(item => <WorkItem work={item} key={item.id} edit={edit} />)
    const addedWorksList = Array.apply(null, {length: worksCount}).map((item, i) => <WorkItem key={i} edit={edit} />);

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={portfolio(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>
                    Work experience
                    {edit &&
                    <span onClick={() => setWorksCount(worksCount + 1)}
                          className='section-title__text--clicked'>+</span>
                    }
                </div>
            </div>
            <div className='talent-worklist'>
                {worksList}
                {addedWorksList}
            </div>
        </div>
    )
}

const WorkItem = (props) => {
    const {work = {}, edit} = props;
    const {company, position, description, start, end} = work;
    const deleteWork = (e) => {
        e.target.parentNode.remove();
    };

    return (
        <div className='talent-worklist__item'>
            {edit && <div onClick={deleteWork} className='delete-row'>&#215;</div>}
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={company || ''}
                        />
                        :
                    <div className='section-row__value'>{company}</div>
                }
            </div>
            <div className='section-row'>
                {
                    edit ?
                        <TextInput
                            type='text'
                            defaultValue={position || ''}
                        />
                        :
                        <div className='section-row__value'>{position}</div>
                }

            </div>
            <div className='section-block'>
                <div className='section-row'>
                    <div className='section-row__title'>Started</div>
                    {
                        edit ?
                            <Calendar
                                name='Started'
                                placeholder='Month, year'
                                halfWidth
                            />
                            :
                            <div className='section-row__value'>{start}</div>
                    }

                </div>
                <div className='section-row'>
                    <div className='section-row__title'>Finished</div>
                    {
                        edit ?
                            <Calendar
                                name='Started'
                                placeholder='Month, year'
                                halfWidth
                            />
                            :
                            <div className='section-row__value'>{end}</div>
                    }

                </div>
            </div>
            <div className='section-row'>
                <div className='section-row__title'>Description</div>
                {
                    edit ?
                        <TextArea
                            type='text'
                            defaultValue={description || ''}
                            height={80}
                        />
                        :
                        <div className='section-row__value'>{description}</div>
                }

            </div>
        </div>
    )

}

export default TalentExperience;