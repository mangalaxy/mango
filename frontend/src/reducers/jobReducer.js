import {TOGGLE_JOB_STATUS} from '../actions/action_types';

// json for test:
const initialState = '[{"id":1,"position":"Senior Backend Engineer","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":true},\
{"id":2,"position":"UX designer","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":true},\
{"id":3,"position":"Front End Developer","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":true},\
{"id":4,"position":"Product Designer Lead","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":false},\
{"id":5,"position":"Project Manager","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":false},\
{"id":6,"position":"Senior Backend Engineer","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":false},\
{"id":7,"position":"Manual Engineer","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":false},\
{"id":8,"position":"Manual Engineer","city":"Seattle","country":"USA","date":"08.09.2018","isChecked":false}]';

export default function (state = JSON.parse(initialState), action) {
  switch(action.type) {
    case TOGGLE_JOB_STATUS: {
      const newState = state.map(item => {
        if (item.id === action.payload.id
          && item.isChecked !== action.payload.isChecked) {          
          return { ...item, isChecked: !item.isChecked} ;          
        }
        else return item;
      });      
      return newState;
    }
    default: return state;
  }  
}