import { h } from 'preact';
import { render } from '@testing-library/preact';
import '@testing-library/jest-dom/extend-expect';
import Test from '../../src/posts/Test';

test('Testing Test Component', () => {
  const { debug, getByTestId } = render(<Test todo="hello" />);
  const element = getByTestId('test-selector');

  expect(element.innerHTML).toBe('Hola Preact! hello');
  debug();
});
