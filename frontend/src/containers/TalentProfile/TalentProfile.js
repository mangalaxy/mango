import React, {useState} from 'react';
import MainMenu from "../../components/Main/MainMenu/MainMenu";
import './TalentProfile.scss';
import {blog} from "../../assets/icons";
import SvgIcon from '../../components/SvgIcon/SvgIcon';
import FormButton from "../../components/Buttons/FormButton/FormButton";
import TalentHeader from './TalentHeader/TalentHeader';
import TalentContacts from './TalentContacts/TalentContacts';
import TalentLinks from './TalentLinks/TalentLinks';
import TalentCondition from './TalentCondition/TalentCondition';
import TalentSalary from './TalentSalary/TalentSalary';
import TalentExpectations from './TalentExpectations/TalentExpectations';
import TalentLocation from './TalentLocation/TalentLocation';
import TalentRoles from './TalentRoles/TalentRoles';
import TalentSkills from './TalentSkills/TalentSkills';
import TalentExperience from './TalentExperience/TalentExperience';
import TalentEducation from './TalentEducation/TalentEducation';
import TalentSertificates from './TalentCertificates/TalentSertificates';
import TalentLanguages from './TalentLanguages/TalentLanguages';
import TalentEditions from './TalentEditions/TalentEditions'

const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];

const mockUser = {
    avatarUrl: 'https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
    name: 'Adam Armstrong',
    speciality: 'UX/UI designer',
    jobStatus: 'jobStatus',
    condition: 'full time',
    email: 'testEmail@gmail.com',
    phone: '097-123-45-67',
    portfolio: 'www.example.com',
    blog: 'www.example.blog',
    salary: '150.000.000',
    certificates: 'Lorem ipsum of designing production-ready business solution for B2B segment',
    expectations: 'Product Company',
    location: {country: 'Ukraine', city: 'Kyiv'},
    userLanguages: [
        {
            id: 1,
            name: 'english',
            level: 'intermediate'
        }
    ],
    userEducations: [
        {
            id: 1,
            university: 'Kyiv Technological university',
            educationSpeciality: 'Visual Data Engineer',
            degree: 'Master',
            start: 'May 1990',
            end: 'Sep 1996'
        }
    ],
    userWorkExperience: [
        {
            id: 1,
            company: 'Apple, Inc.',
            position: 'Senior Backend Engineer',
            description: 'Experienced in various technologies including .NET, JavaScript(front-end & back-end), Python, Java, SQL & NoSQL...',
            start: 'May 1990',
            end: 'Sep 1996'
        }
    ],
    experienceAndRoles: {
        jobRole: 'UX designer',
        specialization: 'UX design',
        total: '4 years'
    },

    skills: ['Java', 'Spring Boot', 'Posgres'],
    headline: 'Experienced in various technologies including .NET, JavaScript(front-end & back-end), Python, Java, SQL & NoSQL...',
    objectives: 'Experienced in various technologies including .NET, JavaScript(front-end & back-end), Python, Java, SQL & NoSQL...',
};

function TalentProfile(props) {
    const [edit, setEdit] = useState(false);

    return (
        <div className='talent-profile'>
            <div className='talent-profile--filter'>
            </div>
            <div className='talent-profile__header'>
                <MainMenu grey dark />
            </div>
            <div className='talent-profile__container'>
                <div className='talent-form'>
                    {
                        !edit &&
                        <div className='talent-form__edit' onClick={()=>setEdit(true)}>
                            <SvgIcon type={blog()}/>
                        </div>
                    }
                    <TalentHeader user={mockUser} edit={edit} />
                    <TalentContacts user={mockUser} edit={edit} />
                    <TalentLinks user={mockUser} edit={edit} />
                    <TalentCondition user={mockUser} edit={edit} />
                    <TalentSalary user={mockUser} edit={edit} />
                    <TalentExpectations user={mockUser} edit={edit} />
                    <TalentLocation user={mockUser} edit={edit} />
                    <TalentRoles user={mockUser} edit={edit} />
                    <TalentSkills user={mockUser} edit={edit} />
                    <TalentExperience user={mockUser} edit={edit} />
                    <TalentEducation user={mockUser} edit={edit} />
                    <TalentSertificates user={mockUser} edit={edit} />
                    <TalentLanguages user={mockUser} edit={edit} />
                    <TalentEditions user={mockUser} isEdit={edit} />
                    {
                        edit &&
                        <div className='talent-form__buttons'>
                            <FormButton
                                text='save changes'
                                className='form-button--red'
                            />
                        </div>
                    }
                </div>
            </div>
        </div>
    )
};

export default TalentProfile;