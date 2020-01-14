import {TOGGLE_JOB_STATUS} from '../actions/action_types';

// json for test:
import {testPositions} from './testPositions.js';

const initialState = testPositions;
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