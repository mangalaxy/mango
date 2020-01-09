import {TOGGLE_MARK_TALENT} from "./action_types";

export const toggleMarkTalent = (id) => {
  return {
    type: TOGGLE_MARK_TALENT,
    payload: id
  }
}