import React, {Fragment} from 'react';
import FormButton from "../../../Buttons/FormButton/FormButton";
import TextInput from "../../../inputs/TextInput/TextInput";
import {name, email, phone, location, blog, website, portfolio, linkedIn} from "../../../../assets/icons";

function Step3(props) {
    const {hidden, prev, next} = props;
    return (
        <div className='profile-form__item' hidden={hidden}>
            <div className='profile-form__container'>
                <div className='profile-form__column-left'>
                    <h2 className='profile-form__title'>contacts</h2>
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='Full name'
                        withIcon
                        icon={name()}
                    />
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='E-mail'
                        withIcon
                        icon={email()}
                    />
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='Phone number'
                        withIcon
                        icon={phone()}
                    />
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='Location'
                        withIcon
                        icon={location()}
                    />
                </div>
                <div className='profile-form__column-right'>
                    <h2 className='profile-form__title'>links</h2>
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='Blog'
                        withIcon
                        icon={blog()}
                    />
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='Personal website'
                        withIcon
                        icon={website()}
                    />
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='Portfolio'
                        withIcon
                        icon={portfolio()}
                    />
                    <TextInput
                        label=' '
                        name='salary'
                        type='text'
                        placeholder='LinkedIn'
                        withIcon
                        icon={linkedIn()}
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