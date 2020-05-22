import R_mergeRight from 'ramda/src/mergeRight';
import R_clone from 'ramda/src/clone';
import * as cosaApi from './cosaApi';

const initialState = {
  count: 0
};

const UPDATE_STATE = 'UPDATE_STATE';
/**
 * Es el reducer de la aplicacion.
 * @param {import('../../types').cosaState} state - El estado de la aplicacion.
 * @param {import('../../types').Action} action - Una accion de redux.
 * @returns {import('../../types').cosaState} El nuevo estado del modulo.
 */
export default (state = R_clone(initialState), action) => {
  switch(action.type) {
    case UPDATE_STATE: return R_mergeRight(state, { count: action.payload.count });
    default: return state;
  }
};
/**
 * Incrementa el contador en 1.
 * @returns {Function}
 */
export const initAppAction = () => dispatch => {
  cosaApi.getCounter().then(resp => {
    console.log(resp);
    let count = 0;
    if(resp) {
      count = parseInt(resp, 10);
    }
    dispatch({
      type: UPDATE_STATE,
      payload: {
        count
      }
    });
  }).catch(err => {
    alert(err)
  });
};
/**
 * Incrementa el contador en 1.
 * @returns {Function}
 */
export const addCountAction = () => (dispatch, getState) => {
  const { count } = getState().cosaState;
  const newCount = count + 1;
  cosaApi.saveCounter(`${newCount}`).then(resp => {
    console.log(resp);
    dispatch({
      type: UPDATE_STATE,
      payload: {
        count: newCount
      }
    });
  }).catch(err => {
    alert(err)
  });
};
/**
 * Decrementa el contador en 1.
 * @returns {Function}
 */
export const substractCountAction = () => (dispatch, getState) => {
  const { count } = getState().cosaState;
  const newCount = count - 1;
  cosaApi.saveCounter(`${newCount}`).then(resp => {
    console.log(resp);
    dispatch({
      type: UPDATE_STATE,
      payload: {
        count: newCount
      }
    });
  }).catch(err => {
    alert(err)
  });
};
