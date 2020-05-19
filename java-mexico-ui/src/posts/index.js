/// <reference path="../types.d.ts" />

import 'preact/debug';
import '../styles/styles.less';
import { h, render } from 'preact';
import { Provider } from 'react-redux';
import store from './store';
import App from './App';

render(<Provider store={store}>
  <App nombre={12} />
  <i class="material-icons">face</i>
</Provider>, document.getElementById('posts'));
