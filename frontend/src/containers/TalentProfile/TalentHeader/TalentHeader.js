import React, {useState, useRef} from 'react';
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
];

function TalentHeader(props) {
    const {user, edit} = props;
    const [newAvatar, setUserPhoto] = useState(null);
    const [avatarEditor, toggleAvatarEditor] = useState(false);
    const setEditorRef = useRef();

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
                    <label htmlFor={edit && 'avatar'}>
                        <Avatar
                            img={user.avatarUrl}
                            alt={user.name}
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
                                withIcon
                                type='text'
                                defaultValue={user.name}
                                className='text-input__field--strong-text'
                            />
                            :
                            <div className='talent-profile__headers'>{user.name}</div>
                    }

                    <div className='section-row__description'>
                        <SvgIcon type={location(colors.COLOR_PRIMERY)}/>
                        <div className='section-row__text'>Kyiv, Ukraine</div>
                    </div>
                </div>
                <div className='section-row section-row--margin-bottom'>
                    {
                        edit ?
                            <TextInput
                                withIcon
                                type='text'
                                defaultValue={user.speciality}
                                className='text-input__field--strong-text'
                            />
                            :
                            <div className='talent-profile__headers'>{user.speciality}</div>
                    }

                </div>
                <div className='section-row section-row--margin-top'>
                    <div className='section-row__title'>Job status</div>
                    {
                        edit ?
                            <DropDownSelect
                                name='role'
                                options={mockOptions}
                                multi={false}
                                placeholder='Open to new opportunities'
                            />
                            :
                            <div className='talent-profile__headers--light'>{user.jobStatus}</div>
                    }

                </div>
            </div>
        </div>
    )
}

export default TalentHeader;