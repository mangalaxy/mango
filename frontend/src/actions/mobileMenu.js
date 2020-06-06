import * as TYPES from './action_types';

export const openMobileMenu = () => dispatch => {
    dispatch({type: TYPES.OPEN_MOBILE_MENU, payload: true})
}

export const closeMobileMenu = () => dispatch => {
    dispatch({type: TYPES.OPEN_MOBILE_MENU, payload: false})
}