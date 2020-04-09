import React, {useState} from 'react';
import colors from "../../../constants/colors";
import {languages} from "../../../assets/icons";
import DropDownSelect from "../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
    {label: 'english', value: 'english'},
    {label: 'intermediate', value: 'intermediate'},
];


function TalentLanguages(props) {
    const {user, edit, onSelect} = props;
    const languagesList = user.languages.map((item, index) => <LenguageItem user={user} key={index} language={item} edit={edit} onSelect={onSelect} index={index}/>)

    const addNewLanguage = () => {
        const {languages} = user;
        onSelect('languages', languages.concat([{}]));
    }

    return (
        <div className='talent-form__section'>
            <div className='section-title'>
                <SvgIcon type={languages(colors.COLOR_PRIMERY)}/>
                <div className='section-title__text'>Languages
                    {edit && <span onClick={addNewLanguage} className='section-title__text--clicked'>+</span>}
                </div>
            </div>
            <div className='section-row talent-languages'>
                {languagesList}
            </div>
        </div>
    )
}

const LenguageItem = (props) => {
    const {user, edit, onSelect, index} = props;
    const {name, level} = user.languages[index];

    const nameValue = mockOptions.filter(option => user.languages[index].name === option.value);
    const levelValue = mockOptions.filter(option => user.languages[index].level === option.value);

    const deleteLanguage = (e) => {
        const languageList = user.languages.filter((language, i) => i !== index);
        onSelect(onSelect('languages', languageList));
    }

    return (
        <div className='talent-languages__item'>
            {edit && <div onClick={deleteLanguage} className='delete-row'>&#215;</div>}
            {
                edit ?
                    <DropDownSelect
                        name={`languages.${index}.name`}
                        options={mockOptions}
                        multi={false}
                        halfWidth
                        onChange={value => onSelect(`languages.${index}.name`, value)}
                        values={nameValue}
                    />
                    :
                    <div className='section-row__title'>{name}</div>
            }
            {
                edit ?
                    <DropDownSelect
                        name={`languages.${index}.level`}
                        options={mockOptions}
                        multi={false}
                        onChange={value => onSelect(`languages.${index}.level`, value)}
                        halfWidth
                        values={levelValue}
                    />
                    :
                    <div className='section-row__value'>{level}</div>
            }
        </div>
    )
}

export default TalentLanguages;