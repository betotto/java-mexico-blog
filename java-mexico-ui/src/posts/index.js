import 'preact/debug';
import '../../styles/styles.less';
import { h, render } from 'preact';
import { Provider } from 'react-redux';
import store from './store';
import App from './App';
import Test from './Test';
import { Machine, interpret } from 'xstate';
import { from } from 'rxjs';

const machine = Machine({
  id: 'light',
  initial: 'green',
  states: {
    green: {
      on: {
        CHANGE: 'yellow'
      }
    },
    yellow: {
      on: {
        CHANGE: 'red'
      }
    },
    red: {
      on: {
        CHANGE: 'red'
      }
    }
  }
});

const service = interpret(machine).start();

const stateX = from(service);

stateX.subscribe(state => {
  console.log(state.value);
});

render(<Provider store={store}>
  <App nombre={12} />
  <Test todo="hello" />
  <i className="material-icons">face</i>
</Provider>, document.getElementById('posts'));

service.start();

// Send events
service.send('CHANGE');
service.send('CHANGE');
service.send('CHANGE');
service.send('CHANGE');
service.send('CHANGE');

// Stop the service when you are no longer using it.
service.stop();
