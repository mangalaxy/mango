import React from 'react';
import {Field} from 'formik';
import './styles.scss';
import FKTextAreaInput from '../../../Fields/FKTextAreaInput/FKTextAreaInput';
import TextInput from '../../../Fields/CommonTextInput/TextInput';
import FKDropdown from '../../../Fields/FKDropdown/FKDropdown';

const EmployersCompanyEditFormView = ({
                                        handleSubmit,
                                        isSubmitting,
                                        isValid,
                                        values,
                                        initialValues,
                                      }) => (
    <div className='employersCompanyEditForm'>
      <div className="headSection">
        <div className="logoContainer">
          {values.logo ? <img src={values.logo}/> :
              <div className='logoPlaceholder'>
                <svg width="110" height="100" viewBox="0 0 110 100" fill="none"
                     xmlns="http://www.w3.org/2000/svg">
                  <path
                      d="M110 12.6052V65.5461H101.399V12.6052C101.399 10.2881 99.4693 8.40316 97.0976 8.40316H12.9024C10.5307 8.40316 8.6013 10.2881 8.6013 12.6052V44.4009L26.3947 18.9946L40.6667 36.4233L47.1339 27.3412L74.7337 62.3944V65.5461H66.3632L47.483 41.5665L23.3139 75.5088L16.2526 70.7099L35.5432 43.6187L26.9344 33.1059L8.6013 59.2829V79.8321C8.6013 82.1483 10.5307 84.0333 12.9024 84.0333H56.8858V92.4372H12.9024C5.78819 92.4372 0 86.7824 0 79.8321V12.6052C0 5.65485 5.78819 0 12.9024 0H97.0976C104.212 0 110 5.65404 110 12.6052ZM81.1849 40.3358C74.0707 40.3358 68.2825 34.681 68.2825 27.7307C68.2825 20.7804 74.0707 15.1255 81.1849 15.1255C88.2991 15.1255 94.0873 20.7804 94.0873 27.7307C94.0873 34.681 88.2991 40.3358 81.1849 40.3358ZM85.486 27.7307C85.486 25.4136 83.5566 23.5295 81.1849 23.5295C78.8132 23.5295 76.8839 25.4136 76.8839 27.7307C76.8839 30.0477 78.8132 31.9327 81.1849 31.9327C83.5566 31.9327 85.486 30.0477 85.486 27.7307ZM91.9372 56.3026H83.335V73.9493H65.2722V82.3525H83.335V100H91.9372V82.3525H110V73.9493H91.9372V56.3026Z"
                      fill="#E0E0E0"/>
                </svg>
                <span>Place logo here</span>
              </div>}
        </div>

        <div className="fieldContainer">
          <label className='fieldLabel'>Company name</label>
          <Field
              component={TextInput}
              disabled={isSubmitting}
              name="name"
          />
          <label className='fieldLabel'>Headline</label>
          <Field
              component={FKTextAreaInput}
              resize
              disabled={isSubmitting}
              name='headline'
          />
          <label className='fieldLabel'>Company address</label>
          <Field
              component={FKTextAreaInput}
              resize
              disabled={isSubmitting}
              name='location'
          />
          <div className="flex">
            <div className={'companySize'}>
              <label className='fieldLabel'>Company size</label>
              <Field
                  component={FKDropdown}
                  disabled={isSubmitting}
                  name="employersCountRange"
                  options={initialValues.employersRanges}
              />
            </div>
            <div className={'category'}>
              <label className='fieldLabel'>Industry</label>
              <Field
                  component={FKDropdown}
                  disabled={isSubmitting}
                  name="category"
                  options={initialValues.industries}
              />
            </div>
          </div>
        </div>

      </div>


      <div className='buttonsContainer'>
        <button className='actionButton' type='submit'
                disabled={!isValid || isSubmitting}
                onClick={handleSubmit}>
          Save changes
        </button>
      </div>


    </div>
);

export default EmployersCompanyEditFormView;
