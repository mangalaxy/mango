import {DELETE_TALENT, TOGGLE_MARK_TALENT} from '../actions/action_types';
// json for test:
import {testTalents} from './testTalents.js';

const initialState = testTalents;
export default function (state = JSON.parse(initialState), action) {
  switch(action.type) {    
    case TOGGLE_MARK_TALENT: {
      const newState = state.map(item => {
        if(item.id === action.payload) {
          item.bookmarked = !item.bookmarked;
          return item;
        } else {
            return item;
        }
      });
      return newState;
    }
    case DELETE_TALENT: {
      const newState = state.filter((item) => 
        item.id != action.payload);
      return newState;      
    }

    default: return state;      
  }  
}