import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import user from './user';
import jobReducer from './jobReducer';

export default combineReducers({
  routing: routerReducer,
  user,
  jobReducer,
});