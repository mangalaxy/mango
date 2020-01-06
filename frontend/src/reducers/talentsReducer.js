// json for test:
import {testTalents} from './testTalents.js';

const initialState = testTalents;
export default function (state = JSON.parse(initialState), action) {
  switch(action.type) {

    default: return state;      
  }  
}