import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import user from './user';
import jobsReducer from './jobsReducer';
import talentsReducer from './talentsReducer';
import {reducer as formReducer} from 'redux-form';

export default combineReducers({
  routing: routerReducer,
  user,
  jobsReducer,
  talentsReducer,
  form: formReducer
});