import 'preact/debug';
import '../../styles/styles.less';
import { CacheProvider, css } from '@emotion/core';
import createCache from '@emotion/cache';

import { h, render } from 'preact';
import { Provider } from 'react-redux';
import store from './store';
import App from './App';
import Test from './Test';
import { Machine, interpret } from 'xstate';
import { from } from 'rxjs';

const postCache = createCache({
  key: 'post',
});

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

render(<CacheProvider value={postCache}>
  <Provider store={store}>
    <App nombre={12} />
    <Test todo="hello" />
    <i className="material-icons">face</i>
  </Provider>
</CacheProvider>, document.getElementById('posts'));

service.start();

// Send events
service.send('CHANGE');
service.send('CHANGE');
service.send('CHANGE');
service.send('CHANGE');
service.send('CHANGE');

// Stop the service when you are no longer using it.
service.stop();
