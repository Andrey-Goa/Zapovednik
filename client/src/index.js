import React from 'react';
import ReactDOM from 'react-dom';
import {Store, App} from './App';
import './index.css';

const store = new Store();

ReactDOM.render(
  <App store={store}/>,
  document.getElementById('root')
);
