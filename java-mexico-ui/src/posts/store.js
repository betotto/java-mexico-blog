
import { combineReducers, createStore, applyMiddleware } from 'redux';
import cosaState from './modules/cosaModule';
import reduxThunk from 'redux-thunk';

const rootReducer = combineReducers({
  cosaState
});
/**
 * @typedef DefaultRootState
 * @property {import('../types').cosaState} cosaState
 */
const store = createStore(rootReducer, applyMiddleware(
  reduxThunk
));

export default store;
