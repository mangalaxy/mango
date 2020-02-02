import React, {Fragment, useState} from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import './Calendar.scss';

function Calendar(props) {
    const {name, label, type, input, placeholder, defaultValue, halfWidth, className} = props;
    const [startDate, setStartDate] = useState('');
    const handleChange = (date) => {
        setStartDate(date);
    }
    return (
        <div className={`calendar ${halfWidth && 'calendar--half-width'}`}>
            {label && <label className="control-label">{label}</label>}
            <div className='calendar__wrapper'>
                <DatePicker
                    name={name}
                    selected={startDate}
                    onChange={handleChange}
                    dateFormat="MMM yyyy"
                    showMonthYearPicker
                    placeholderText={placeholder || ''}
                />
                <Down />
            </div>
        </div>
    )
}

const Down = (props) => {
    return (<svg
        className='datepicker-chewron'
        width="9" height="9" viewBox="0 0 17 10"
        fill="none" xmlns="http://www.w3.org/2000/svg">
        <path
            d="M16.8301 1.03084L15.9785 0.171877C15.865 0.057172 15.7343 0 15.5865 0C15.4391 0 15.3084 0.057172 15.1949 0.171877L8.50009 6.92438L1.80555 0.172058C1.69201 0.0573527 1.56135 0.000180444 1.41374 0.000180444C1.26607 0.000180444 1.13541 0.0573527 1.02192 0.172058L0.1704 1.03108C0.0566808 1.14555 0 1.27734 0 1.42629C0 1.57512 0.0568598 1.70692 0.1704 1.82138L8.10828 9.82824C8.22176 9.94277 8.35248 10 8.50009 10C8.6477 10 8.77818 9.94277 8.8916 9.82824L16.8301 1.82138C16.9436 1.70686 17 1.57506 17 1.42629C17 1.27734 16.9436 1.14555 16.8301 1.03084Z"
            fill="#000"/>
    </svg>);
};

export default Calendar;