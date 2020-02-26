import React from 'react';
import FormButton from "../../../Buttons/FormButton/FormButton";
import TextInput from "../../../inputs/TextInput/TextInput";
import {name, email, phone, location, blog, website, portfolio, linkedIn} from "../../../../assets/icons";

function Step3(props) {
    const {hidden, prev, next, profile, inputChange, onSelect} = props;

    return (
        <div className='profile-form__item' hidden={hidden}>
            <div className='profile-form__container'>
                <div className='profile-form__column-left'>
                    <h2 className='profile-form__title'>contacts</h2>
                    <TextInput
                        label=''
                        name='talent.fullName'
                        type='text'
                        placeholder='Full name'
                        withIcon
                        icon={name()}
                        onChange={inputChange}
                        value={profile.talent.fullName}
                    />
                    <TextInput
                        label=''
                        name='talent.email'
                        type='text'
                        placeholder='E-mail'
                        withIcon
                        icon={email()}
                        onChange={inputChange}
                        value={profile.talent.email}
                    />
                    <TextInput
                        label=''
                        name='phone'
                        type='text'
                        placeholder='Phone number'
                        withIcon
                        icon={phone()}
                        onChange={inputChange}
                        value={profile.phone}
                    />
                    <TextInput
                        label=''
                        name='talent.location'
                        type='text'
                        placeholder='Location'
                        withIcon
                        icon={location()}
                        onChange={inputChange}
                        value={profile.talent.location}
                    />
                </div>
                <div className='profile-form__column-right'>
                    <h2 className='profile-form__title'>links</h2>
                    <TextInput
                        label=''
                        name='links.blog'
                        type='text'
                        placeholder='Blog'
                        withIcon
                        icon={blog()}
                        onChange={inputChange}
                        value={profile.links.blog}
                    />
                    <TextInput
                        label=''
                        name='links.website'
                        type='text'
                        placeholder='Personal website'
                        withIcon
                        icon={website()}
                        onChange={inputChange}
                        value={profile.links.website}
                    />
                    <TextInput
                        label=''
                        name='links.portfolio'
                        type='text'
                        placeholder='Portfolio'
                        withIcon
                        icon={portfolio()}
                        onChange={inputChange}
                        value={profile.links.portfolio}
                    />
                    <TextInput
                        label=''
                        name='links.linkedIn'
                        type='text'
                        placeholder='LinkedIn'
                        withIcon
                        icon={linkedIn()}
                        onChange={inputChange}
                        value={profile.links.linkedIn}
                    />
                </div>
                <div className='buttons-container'>
                    <FormButton
                        text='Previous'
                        className='form-button--white'
                        onClick={prev}
                    />
                    <FormButton
                        text='Next'
                        className='form-button--red'
                        onClick={next}
                    />
                </div>
            </div>
        </div>
    )
}

export default Step3;