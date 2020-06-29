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
import TalentSertificates from './TalentCertificates/TalentCertificates';
import TalentLanguages from './TalentLanguages/TalentLanguages';
import TalentEditions from './TalentEditions/TalentEditions'
import {useFormik} from 'formik';

const mockProfile = {
  id: 3256,
  talent: {
    id: 3256,
    fullName: "Arthur van Hoff",
    email: "arthur12.vhoff@gmail.com",
    location: {
      id: 5,
      city: "Hamburg",
      country: "Germany"
    },
  },
  photoURL: "https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
  jobRole: "Software Engineering",
  specialization: "Backend Software Engineering",
  jobTitle: "Senior Python Developer",
  status: "Open for new job offers",
  phone: "+49 30 462988844",
  employmentType: "Full-time",
  prefferedLocation: {
    id: 7,
    city: "Berlin",
    country: "Germany"
  },
  salary: 15000000,
  links: {
    portfolio: "www.example.portfolio.org",
    blog: "www.example.org/blog"
  },
  expectations: ["Start-up company","Outsourcing company","Product company"],
  experience: [
    {
      company: "Spikework",
      position: "Senior Python Engineer",
      startDate: '2017-03-01',
      endDate: '2019-07-01',
      description: 'Experienced in various technologies including .NET, JavaScript(front-end & back-end), Python, Java, SQL & NoSQL...',
    }
  ],
  skills: ['Java', 'Spring Boot', 'Postgres'],
  certificates: 'Lorem ipsum of designing production-ready business solution for B2B segment',
  headline: 'Experienced in various technologies including .NET, JavaScript(front-end & back-end), Python, Java, SQL & NoSQL...',
  objectives: 'Experienced in various technologies including .NET, JavaScript(front-end & back-end), Python, Java, SQL & NoSQL...',
  education: [
    {
      id: 1,
      university: 'Kyiv Technological university',
      speciality: 'Visual Data Engineer',
      degree: 'Master',
      start: 'May 1990',
      end: 'Sep 1996'
    }
  ],
  languages: [
    {
      id: 1,
      name: 'english',
      level: 'intermediate'
    }
  ],
};

function TalentProfile(props) {
  const [edit, setEdit] = useState(false);

  const formik = useFormik({
    initialValues: {...mockProfile},
    onSubmit: values => {
      console.log(values);
    },
  });

  return (
        <div className='talent-profile'>
          <div className='talent-profile--filter'>
          </div>
          <div className='talent-profile__header'>
            <MainMenu grey dark />
          </div>
          <div className='talent-profile__container'>
            <form className='talent-form'>
              {
                !edit &&
                <div className='talent-form__edit' onClick={()=>setEdit(true)}>
                  <SvgIcon type={blog()}/>
                </div>
              }
              <TalentHeader user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentContacts user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentLinks user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentCondition user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentSalary user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentExpectations user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentLocation user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentRoles user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentSkills user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentExperience user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentEducation user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentSertificates user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentLanguages user={formik.values} edit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              <TalentEditions user={formik.values} isEdit={edit} onСhange={formik.handleChange} onSelect={formik.setFieldValue} />
              {
                edit &&
                <div className='talent-form__buttons'>
                  <FormButton
                        text='save changes'
                        className='form-button--red'
                        onClick={formik.submitForm}
                  />
                </div>
              }
            </form>
          </div>
        </div>
  )
}

export default TalentProfile;