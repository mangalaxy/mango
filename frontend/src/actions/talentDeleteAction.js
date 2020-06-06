import {DELETE_TALENT} from "./action_types";

export const talentDeleteAction = (id) => {
  return {
    type: DELETE_TALENT,
    payload: id
  }
}