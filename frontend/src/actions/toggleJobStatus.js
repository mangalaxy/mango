import {TOGGLE_JOB_STATUS} from "./action_types";

export const toggleJobStatus = (data) => {
  return {
    type: TOGGLE_JOB_STATUS,
    payload: data
  }
}