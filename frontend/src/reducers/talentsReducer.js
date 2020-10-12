import {DELETE_TALENT, TOGGLE_MARK_TALENT} from '../actions/action_types';
import {matchedTalents} from '../mocks/matchedTalents.js';

const initialState = JSON.parse(matchedTalents);

const talentReducer = (state = initialState, action) => {

  switch(action.type) {    
    case TOGGLE_MARK_TALENT: {
      return state.map(item => {
        if (item.id === action.payload) {
          item.bookmarked = !item.bookmarked;
          return item;
        } else {
          return item;
        }
      });
    }
    case DELETE_TALENT: {
      return state.filter((item) =>
            item.id !== action.payload);
    }
    default:
      return state;
  }  
}

export default talentReducer;