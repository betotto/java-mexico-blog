import { h, JSX }  from 'preact';
import { useSelector, useDispatch } from 'react-redux';
import { addCount, substractCount } from './modules/cosaModule';
/**
 * App is the root component.
 * @param {object} props The properties for the Component.
 * @param {number} props.nombre - Nombre comun.
 * @param {number} [props.todo] - Just a todo number.
 * @returns {JSX.Element}
 */
const App = props => {
  const todo = useSelector(state => state.cosaModule.count);
  const dispatch = useDispatch();
  return <div>
    <h1>
      {`Hola Preact! ${props.nombre} ${todo}`}
    </h1>
    <br />
    <button onClick={() => dispatch(addCount())}>{'   +   '}</button>
    <button onClick={() => dispatch(substractCount())}>{'   -   '}</button>
  </div>;
};

export default App;