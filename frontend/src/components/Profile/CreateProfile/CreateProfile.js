import React, {useState} from 'react';
import './CreateProfile.scss'
import TalentMenu from "../../Talent/TalentMenu/TalentMenu";
import Step1 from "./Step1/Step1";
import Step2 from "./Step2/Step2";
import Step3 from "./Step3/Step3";
import Step4 from "./Step4/Step4";
import Step5 from "./Step5/Step5";
import {useFormik} from 'formik';

function CreateProfile(props) {
    const [activeStep, setActiveStep] = useState(1);

    const formik = useFormik({
        initialValues: {
            jobTitle: '',
            jobRoles: [],
            jobSpecialities: [],
            prefferedLocation: {
                country: '',
                city: ''
            },
            employmentType: '',
            typeOfCompany: '',
            expectations: [],
            salary: '',
            industries: [],
            talent: {
                fullName: '',
                email: '',
                location: '',
            },
            phone: '',
            links: {
                portfolio: '',
                blog: '',
                website: '',
                linkedIn: '',
            },
            experience: [{}],
            education: [{}],
            languages: [{}]

        },
        onSubmit: values => {
            console.log(values);
        },
    });

    return (
            <div className='profile'>
                <div className='profile--filter'>
                </div>
                <div className='profile-header'>
                    <TalentMenu/>
                </div>
                <div className='profile-container'>
                    <div className='profile-form'>
                            <ul className="profile-menu" hidden={activeStep === 5}>
                                <li
                                    className={`profile-menu__item
                                    ${activeStep === 1 ? 'profile-menu__item--active' : ''}
                                    ${activeStep > 1 ? 'profile-menu__item--checked' : ''}
                                    `}>
                                    1
                                </li>
                                <li
                                    className={`profile-menu__item
                                    ${activeStep === 2 ? 'profile-menu__item--active' : ''}
                                    ${activeStep > 2 ? 'profile-menu__item--checked' : ''}
                                    `}>
                                    2
                                </li>
                                <li
                                    className={`profile-menu__item
                                    ${activeStep === 3 ? 'profile-menu__item--active' : ''}
                                    ${activeStep > 3 ? 'profile-menu__item--checked' : ''}
                                    `}>
                                    3
                                </li>
                                <li
                                    className={`profile-menu__item
                                    ${activeStep === 4 ? 'profile-menu__item--active' : ''}
                                    ${activeStep > 4 ? 'profile-menu__item--checked' : ''}
                                    `}>
                                    4
                                </li>
                                <li
                                    className={`profile-menu__item
                                    ${activeStep === 5 ? 'profile-menu__item--active' : ''}`}>
                                    5
                                </li>
                            </ul>
                        <form className='form-container'>
                            <Step1
                                hidden={activeStep !== 1}
                                next={() => setActiveStep(2)}
                                profile={formik.values}
                                inputChange={formik.handleChange}
                                onSelect={formik.setFieldValue}
                            />
                            <Step2
                                hidden={activeStep !== 2}
                                next={() => setActiveStep(3)}
                                prev={() => setActiveStep(1)}
                                profile={formik.values}
                                inputChange={formik.handleChange}
                                onSelect={formik.setFieldValue}
                            />
                            <Step3
                                hidden={activeStep !== 3}
                                next={() => setActiveStep(4)}
                                prev={() => setActiveStep(2)}
                                profile={formik.values}
                                inputChange={formik.handleChange}
                                onSelect={formik.setFieldValue}
                            />
                            <Step4
                                hidden={activeStep !== 4}
                                next={() => setActiveStep(5)}
                                prev={() => setActiveStep(3)}
                                profile={formik.values}
                                inputChange={formik.handleChange}
                                onSelect={formik.setFieldValue}
                            />
                            <Step5
                                hidden={activeStep !== 5}
                                onSubmit={formik.submitForm}
                            />
                        </form>
                    </div>
                </div>
            </div>
    )
}

export default CreateProfile;