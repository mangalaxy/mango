import React, {useState} from 'react';
import colors from "../../../constants/colors";
import {languages} from "../../../assets/icons";
import DropDownSelect from "../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];


function TalentLanguages(props) {
    const {user, edit} = props;
    const {userLanguages} = user;
    const [languagesCount, setLanguagesCount] = useState(0);
    const languagesList = userLanguages.map(item => <LenguageItem language={item} edit={edit} />)
    const addedLanguagesList = Array.apply(null, {length: languagesCount}).map((item,index) => <LenguageItem key={index} edit={edit} />);

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={languages(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Languages
                    {edit && <span onClick={() => setLanguagesCount(languagesCount+1)} className='section-title__text--clicked'>+</span>}
                </div>
            </div>
            <div className='section-row talent-languages'>
                {languagesList}
                {addedLanguagesList}
            </div>
        </div>
    )
}

const LenguageItem = (props) => {
    const {language = {}, edit} = props;
    const {name, level} = language;

    const deleteLanguage = (e) => {
        e.target.parentNode.remove();
    }

    return (
        <div className='talent-languages__item'>
            {edit && <div onClick={deleteLanguage} className='delete-row'>&#215;</div>}
            {
                edit ?
                    <DropDownSelect
                        name='role'
                        options={mockOptions}
                        multi={false}
                        halfWidth
                    />
                    :
                    <div className='section-row__title'>{name}</div>
            }
            {
                edit ?
                    <DropDownSelect
                        name='role'
                        options={mockOptions}
                        multi={false}
                        halfWidth
                    />
                    :
                    <div className='section-row__value'>{level}</div>
            }
        </div>
    )
}

export default TalentLanguages;