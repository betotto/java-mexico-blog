import R_mergeRight from 'ramda/src/mergeRight';
import R_clone from 'ramda/src/clone';

const initialState = {
  count: 0
};

const PLUS_ACTION = 'PLUS_ACTION';
const SUBSTRACT_COUNT = 'SUBSTRACT_COUNT';
/**
 * Es el reducer de la aplicacion.
 * @param {import('../../types').CosaModule} state - El estado de la aplicacion.
 * @param {import('../../types').Action} action - Una accion de redux.
 * @returns {import('../../types').CosaModule} El nuevo estado del modulo.
 */
export default (state = R_clone(initialState), action) => {
  switch(action.type) {
    case PLUS_ACTION: return R_mergeRight(state, { count: state.count + action.payload.value });
    case SUBSTRACT_COUNT: return R_mergeRight(state, { count: state.count + action.payload.value });
    default: return state;
  }
};
/**
 * Incrementa el contador en 1.
 * @returns {import('../../types').Action}
 */
export const addCount = () => ({
  type: PLUS_ACTION,
  payload: {
    value: 1
  }
});
/**
 * Decrementa el contador en 1.
 * @returns {import('../../types').Action}
 */
export const substractCount = () => ({
  type: SUBSTRACT_COUNT,
  payload: {
    value: -1
  }
});
