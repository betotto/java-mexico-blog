import { h, JSX }  from 'preact';
/**
 * For testing purproses.
 * @param {object} props The properties for the Component.
 * @param {string} props.todo - A string.
 * @returns {JSX.Element}
 */
const Test = props => <div>
  <h1 data-testid="test-selector">
    {`Hola Preact! ${props.todo}`}
  </h1>
</div>;

export default Test;
