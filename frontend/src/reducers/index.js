import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import user from './user';
import {reducer as formReducer} from 'redux-form';

export default combineReducers({
  routing: routerReducer,
  user,
  form: formReducer
});
