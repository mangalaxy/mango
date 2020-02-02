import React from 'react';
import MainMenu from "../../components/Main/MainMenu/MainMenu";
import './TalentProfile.scss';
import Avatar from '../../components/Avatar/Avatar';
import TextInput from '../../components/inputs/TextInput/TextInput';
import TextArea from '../../components/inputs/TextArea/TextArea';
import {reduxForm} from "redux-form";
import {name, email, location, portfolio, links, town, clock, salary, brain, education, certificates, languages, edit} from "../../assets/icons";
import SvgIcon from '../../components/SvgIcon/SvgIcon';
import colors from '../../constants/colors'
import DropDownSelect from "../../components/inputs/Select/DropDownSelect/DropDownSelect";
import SimpleSelect from "../../components/inputs/Select/SimpleSelect/SimpleSelect";
import FormButton from "../../components/Buttons/FormButton/FormButton";
import Calendar from "../../components/inputs/Calendar/Calendar";


const mockOptions = [
    {label: 'Option1', value: 'Option1'},
    {label: 'Option2', value: 'Option2'},
    {label: 'Option3', value: 'Option3'},
];

const mockUser = {
    avatarUrl: 'https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
    name: 'Adam Armstrong',
    speciality: 'UX/UI designer',
    email: 'testEmail@gmail.com',
    phone: '097-123-45-67',
    portfolio: 'www.example.com',
    blog: 'www.example.blog',
    salary: '150.000.000',
    company: 'Apple, Inc.',
    position: 'Senior Backend Engineer',
    description: 'Experienced in various technologies including .NET, JavaScript(front-end & back-end), Python, Java, SQL & NoSQL...',
    university: 'Kyiv Technological university',
    educationSpeciality: 'Visual Data Engineer',
    certificates: 'Lorem ipsum of designing production-ready business solution for B2B segment'

};

function TalentProfile(props) {

    return (
        <div className='talent-profile'>
            <div className='talent-profile--filter'>
            </div>
            <div className='talent-profile__header'>
                <MainMenu grey dark />
            </div>
            <div className='talent-profile__container'>
                <div className='talent-form'>
                        <div className='talent-form__header'>
                            <div className='talent-form__section'>
                                <Avatar
                                    img={mockUser.avatarUrl}
                                    alt={mockUser.name}
                                    size={200}
                                />
                            </div>
                            <div className='talent-form__section'>
                                <div className='section-row section-row--margin-bottom'>
                                    <TextInput
                                        withIcon
                                        type='text'
                                        defaultValue={mockUser.name}
                                        className='text-input__field--strong-text'
                                    />
                                    <div className='section-row__description'>
                                        <SvgIcon type={location(colors.COLOR_GREY)}/>
                                        <div className='section-row__text'>Kyiv, Ukraine</div>
                                    </div>
                                </div>
                                <div className='section-row section-row--margin-bottom'>
                                    <TextInput
                                        withIcon
                                        type='text'
                                        defaultValue={mockUser.speciality}
                                        className='text-input__field--strong-text'
                                    />
                                </div>
                                <div className='section-row section-row--margin-top'>
                                    <div className='section-row__title'>Job status</div>
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={false}
                                        placeholder='Open to new opportunities'
                                    />
                                </div>
                            </div>
                        </div>
                        <div className='talent-form__section'>
                          <div className='section-title'>
                              <SvgIcon type={email(colors.COLOR_GREY)}/>
                              <div className='section-title__text'>contact</div>
                          </div>
                          <div className='section-row'>
                              <div className='section-row__title'>Email</div>
                              <TextInput
                                  type='email'
                                  defaultValue={mockUser.email}
                              />
                          </div>
                          <div className='section-row'>
                              <div className='section-row__title'>Phone</div>
                              <TextInput
                                  type='text'
                                  defaultValue={mockUser.phone}
                              />
                          </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={links(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>links</div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Portfolio</div>
                                <TextInput
                                    type='text'
                                    defaultValue={mockUser.portfolio}
                                />
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Blog</div>
                                <TextInput
                                    type='text'
                                    defaultValue={mockUser.blog}
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={clock(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Condition</div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Choose type of employment</div>
                                <DropDownSelect
                                    name='role'
                                    options={mockOptions}
                                    multi={false}
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={salary(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Salary</div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Expectations:</div>
                                <TextInput
                                    type='text'
                                    defaultValue={mockUser.salary}
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={town(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Expectations</div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Type of company:</div>
                                <SimpleSelect
                                    options={mockOptions}
                                    multi={true}
                                    directionTable
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={location(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Location</div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Want to work in the area:</div>
                                <DropDownSelect
                                    name='role'
                                    options={mockOptions}
                                    multi={false}
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={brain(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Experience & Roles</div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Job role</div>
                                <DropDownSelect
                                    name='role'
                                    options={mockOptions}
                                    multi={false}
                                />
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Specializations</div>
                                <SimpleSelect
                                    options={mockOptions}
                                    multi={true}
                                    directionTable
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={name(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Skills</div>
                            </div>
                            <div className='sectin-row section-row--margin-top'>
                                <div className='section-row'>
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={true}
                                        placeholder='Programming Languages (Sripting and Markup)'
                                    />
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={true}
                                        placeholder='Frameworks'
                                    />
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={true}
                                        placeholder='Libraries'
                                    />
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={true}
                                        placeholder='Tools'
                                    />
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={true}
                                        placeholder='Databases'
                                    />
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={true}
                                        placeholder='Platforms'
                                    />
                                    <DropDownSelect
                                        name='role'
                                        options={mockOptions}
                                        multi={true}
                                        placeholder='Development Environments'
                                    />
                                </div>
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={portfolio(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Work experience</div>
                            </div>
                            <div className='section-row'>
                                <TextInput
                                    type='text'
                                    defaultValue={mockUser.company}
                                />
                            </div>
                            <div className='section-row'>
                                <TextInput
                                    type='text'
                                    defaultValue={mockUser.position}
                                />
                            </div>
                            <div className='section-block'>
                                <div className='section-row'>
                                    <div className='section-row__title'>Started</div>
                                    <Calendar
                                        name='Started'
                                        placeholder='Month, year'
                                        halfWidth
                                    />
                                </div>
                                <div className='section-row'>
                                    <div className='section-row__title'>Finished</div>
                                    <Calendar
                                        name='Started'
                                        placeholder='Month, year'
                                        halfWidth
                                    />
                                </div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Description</div>
                                <TextArea
                                    type='text'
                                    defaultValue={mockUser.description}
                                    height={80}
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={education(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Education</div>
                            </div>
                            <div className='section-row'>
                                <TextInput
                                    type='text'
                                    defaultValue={mockUser.university}
                                />
                            </div>
                            <div className='section-row'>
                                <TextInput
                                    type='text'
                                    defaultValue={mockUser.educationSpeciality}
                                />
                            </div>
                            <div className='section-row section-row--margin-top'>
                                <DropDownSelect
                                    name='role'
                                    options={mockOptions}
                                    multi={false}
                                    halfWidth
                                />
                            </div>
                            <div className='section-block'>
                                <div className='section-row'>
                                    <div className='section-row__title'>Started</div>
                                    <Calendar
                                        name='Started'
                                        placeholder='Month, year'
                                        halfWidth
                                    />
                                </div>
                                <div className='section-row'>
                                    <div className='section-row__title'>Finished</div>
                                    <Calendar
                                        name='Started'
                                        placeholder='Month, year'
                                        halfWidth
                                    />
                                </div>
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={certificates(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>licenses and certificates</div>
                            </div>
                            <div className='section-row'>
                                <TextArea
                                    type='text'
                                    defaultValue={mockUser.certificates}
                                    height={80}
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={languages(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Languages <span className='section-title__text--clicked'>+</span></div>
                            </div>
                            <div className='section-row'>
                                <div className='delete-row'>&#215;</div>
                                <DropDownSelect
                                    name='role'
                                    options={mockOptions}
                                    multi={false}
                                    halfWidth
                                />
                                <DropDownSelect
                                    name='role'
                                    options={mockOptions}
                                    multi={false}
                                    halfWidth
                                />
                            </div>
                        </div>
                        <div className='talent-form__section'>
                            <div className='section-title'>
                                <SvgIcon type={edit(colors.COLOR_GREY)}/>
                                <div className='section-title__text'>Additional sections</div>
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Headline</div>
                                <TextArea
                                    type='text'
                                    height={80}
                                    placeholder='Owner of UX product'
                                />
                            </div>
                            <div className='section-row'>
                                <div className='section-row__title'>Objectives</div>
                                <TextArea
                                    type='text'
                                    height={80}
                                    placeholder='Shiping 15+ successful app'
                                />
                            </div>
                        </div>
                        <div className='talent-form__buttons'>
                            <FormButton
                                text='save changes'
                                className='form-button--red'
                            />
                        </div>
                    </div>
            </div>
        </div>
    )
};

TalentProfile = reduxForm ({
    form: 'talentProfile',
}) (TalentProfile);

export default TalentProfile;