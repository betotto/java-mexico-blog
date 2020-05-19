
import { combineReducers, createStore, applyMiddleware } from 'redux';
import cosaModule from './modules/cosaModule';
import reduxThunk from 'redux-thunk';

const rootReducer = combineReducers({
  cosaModule
});
/**
 * @typedef DefaultRootState
 * @property {import('../types').CosaModule} cosaModule
 */
const store = createStore(rootReducer, applyMiddleware(
  reduxThunk
));

export default store;
