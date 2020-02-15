import React, {useState} from 'react';
import {connect} from 'react-redux';
import Select from "react-dropdown-select";
import {InputTextarea} from 'primereact/inputtextarea';
import FormButton from '../../Buttons/FormButton/FormButton';
import './RequestInterview.scss';

const RequestInterview = (props) => {

  const [selectValues, setSelectValues] = useState([]);
  const [textareaValue, setTextareaValue] = useState('Hi, Arthur! I would like to invite you for an interview ASAP.');

  const onChangeSelect = (values) => {
    setSelectValues(values);    
  };
  const onChangeTextarea = (e) => {
    setTextareaValue(e.target.value);    
  };
  const sendForm = () => {
    let error = document.querySelector('.interview-error');
    if(textareaValue && selectValues[0]) {
      error.innerText = '';
      return {textareaValue, selectValue: selectValues[0].place};
    } else {
      error.innerText = 'All fields are required!';
      return;
    }      
  }

  let talentData = props.talents.filter(item =>    
    item.id === +props.talentId
  );
  
  const {position, salary} = talentData[0];

  const options = [{id:1, place:'Office'},
                   {id:2, place:'Video chat'},
                   {id:2, place:'Phone'}
                  ];
  return(
    <div className="interview">
      <h2 className="interview-title">
        Request Interview
      </h2>
      <h3 className="interview-itemTitle">
        Position
      </h3>
      <p className="interview-itemTitle-description">
        {position}
      </p>
      <h3 className="interview-itemTitle">
        Base Salary
      </h3>
      <p className="interview-itemTitle-description">
        {salary}
      </p>
      <h3 className="interview-itemTitle">
        Place
      </h3>
      <form>
        <Select options={options}              
                onChange={onChangeSelect}
                values={selectValues}
                valueField={'place'}
                labelField={'place'}
                className="interview-select"
                required={'required'}
        />
        <h3 className="interview-itemTitle">
          Personal message
        </h3>

        <InputTextarea className="interview-textArea"
                       autoResize={true}
                       onChange={onChangeTextarea}
                       value={textareaValue}                       
        />
        <div className="interview-error"></div>
        <FormButton text="SEND REQUEST"
                    className="interview-btn"
                    onClick={sendForm}/>
      </form>
    </div>
  )
}
const mapStoreToProps = (store) => (
  {talents: store.talentsReducer}
)
export default connect(mapStoreToProps)(RequestInterview);