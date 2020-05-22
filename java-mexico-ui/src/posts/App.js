import { h, JSX }  from 'preact';
import { useEffect } from 'preact/hooks';
import { css } from '@emotion/core';
import styled from '@emotion/styled';
import { useSelector, useDispatch } from 'react-redux';
import * as cosaModule from './modules/cosaModule';
/**
 * App is the root component.
 * @param {object} props The properties for the Component.
 * @param {number} props.nombre - Nombre comun.
 * @param {number} [props.todo] - Just a todo number.
 * @returns {JSX.Element}
 */
const App = props => {
  const todo = useSelector(state => state.cosaState.count);
  const dispatch = useDispatch();
  useEffect(() => {
    if(todo === 0) {
      dispatch(cosaModule.initAppAction());
    }
  });
  return <AppComponent color="green">
    <h1>
      {`Hola Preact! ${props.nombre} ${todo}`}
    </h1>
    <br />
    <button onClick={() => dispatch(cosaModule.addCountAction())}>{'   +   '}</button>
    <button onClick={() => dispatch(cosaModule.substractCountAction())}>{'   -   '}</button>
  </AppComponent>;
};

const AppStyles = props => {
  let color = 'red';
  if(props.color) {
    color = props.color;
  }
  return css`
    color: ${color};
    background-color: cyan;
  `;
};

const AppComponent = styled('div')`
  ${AppStyles};
`;

export default App;
