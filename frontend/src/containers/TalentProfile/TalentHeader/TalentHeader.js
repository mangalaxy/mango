import React, {useRef, useState} from 'react';
import colors from "../../../constants/colors";
import {location} from "../../../assets/icons";
import Avatar from '../../../components/Avatar/Avatar';
import TextInput from '../../../components/inputs/TextInput/TextInput';
import DropDownSelect from "../../../components/inputs/Select/DropDownSelect/DropDownSelect";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import AvatarEditor from 'react-avatar-editor'

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
    {label: 'Open for new job offers', value: 'Open for new job offers'},
];

function TalentHeader(props) {
    const {user, edit, onСhange, onSelect} = props;
    const [newAvatar, setUserPhoto] = useState(null);
    const [avatarEditor, toggleAvatarEditor] = useState(false);
    const setEditorRef = useRef();
    const status = mockOptions.filter(option => user.status === option.value);

    const uploadPhoto = (e) => {
        const file = e.target.files[0];
        setUserPhoto(file);
        toggleAvatarEditor(true);
    }

    return (
        <div className='talent-form__header'>
            <div className='talent-form__section'>
                <input type='file' name='file' id='avatar' hidden onChange={uploadPhoto}/>
                {
                    !avatarEditor &&
                    <label htmlFor={edit ? 'avatar' : ''}>
                        <Avatar
                            img={user.photoURL}
                            alt={user.talent.fullName}
                            size={200}
                            edit={edit}
                        />
                    </label>
                }
                {
                    avatarEditor &&
                    <div className='talent-avatar-editor'>
                        <AvatarEditor
                            ref={setEditorRef}
                            image={newAvatar}
                            width={200}
                            height={200}
                            border={0}
                            rotate={0}
                            scale={1.2}
                        />
                    </div>
                }

            </div>
            <div className='talent-form__section'>
                <div className='section-row section-row--margin-bottom'>
                    {
                        edit ?
                            <TextInput
                                name='talent.fullName'
                                withIcon
                                type='text'
                                onChange={onСhange}
                                value={user.talent.fullName}
                                defaultValue={user.talent.fullName}
                                className='text-input__field--strong-text'
                            />
                            :
                            <div className='talent-profile__headers'>{user.talent.fullName}</div>
                    }

                    <div className='section-row__description'>
                        <SvgIcon type={location(colors.COLOR_PRIMERY)}/>
                        <div className='section-row__text'>{user.talent.location.city}, {user.talent.location.country}</div>
                    </div>
                </div>
                <div className='section-row section-row--margin-bottom'>
                    {
                        edit ?
                            <TextInput
                                name='jobTitle'
                                withIcon
                                type='text'
                                onChange={onСhange}
                                value={user.jobTitle}
                                defaultValue={user.jobTitle}
                                className='text-input__field--strong-text'
                            />
                            :
                            <div className='talent-profile__headers'>{user.jobTitle}</div>
                    }

                </div>
                <div className='section-row section-row--margin-top'>
                    <div className='section-row__title'>Job status</div>
                    {
                        edit ?
                            <DropDownSelect
                                name='status'
                                options={mockOptions}
                                multi={false}
                                placeholder='Open to new opportunities'
                                onChange={value => onSelect('status', value)}
                                values={status}
                            />
                            :
                            <div className='talent-profile__headers--light'>{user.status}</div>
                    }

                </div>
            </div>
        </div>
    )
}

export default TalentHeader;