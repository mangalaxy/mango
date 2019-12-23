import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import user from './user';
import jobReducer from './jobReducer';
import {reducer as formReducer} from 'redux-form';

export default combineReducers({
  routing: routerReducer,
  user,
  jobReducer,
  form: formReducer
});