import React from 'react';
import ReactDOM from 'react-dom';
import {App, PhotoStore, TagStore} from './App';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App photoStore={new PhotoStore()} tagStore={new TagStore()}/>, div);
});
