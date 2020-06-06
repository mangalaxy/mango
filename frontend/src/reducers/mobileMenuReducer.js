import * as TYPES from '../actions/action_types';

const initialState = {
    mobileMenuOpen: false
}

const mobileMenuReducer = (state = initialState, action) => {
    switch (action.type) {
        case TYPES.OPEN_MOBILE_MENU:
            return {...state, mobileMenuOpen: action.payload}
        case TYPES.CLOSE_MOBILE_MENU:
            return {...state, mobileMenuOpen: action.payload}
        default:
            return {...state}
    }
}

export default mobileMenuReducer;