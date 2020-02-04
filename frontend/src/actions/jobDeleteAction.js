import {DELETE_JOB} from "./action_types";

export const jobDeleteAction = (id) => {
  return {
    type: DELETE_JOB,
    payload: id
  }
}