import {DELETE_JOB, TOGGLE_JOB_STATUS} from '../actions/action_types';
import {employerJobs} from '../mocks/employerJobs.js';

const initialState = employerJobs;

const jobsReducer = (state = initialState, action) => {

  switch(action.type) {
    case TOGGLE_JOB_STATUS: {
      return state.map(item => {
        if (item.id === action.payload.id && item.isChecked !== action.payload.isChecked) {
          return {
            ...item,
            isChecked: !item.isChecked
          };
        } else
          return item;
      })
    }
    case DELETE_JOB: {
      return state.filter(item => item.id !== action.payload);
    }
    default:
      return state;
  }  
}

export default jobsReducer;