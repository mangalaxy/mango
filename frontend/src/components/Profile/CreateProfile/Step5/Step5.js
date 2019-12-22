import React, {Fragment} from 'react';
import FormButton from "../../../Buttons/FormButton/FormButton";

function Step5(props) {
    const {hidden} = props;
    return (
        <div className='profile-form__item profile-form__item--last' hidden={hidden}>
            <div>
                <h1 className='last-title'>Your profile is ready!</h1>
                <FormButton
                    text='Got it'
                    className='form-button--red'
                />
            </div>
        </div>
    )
}

export default Step5;