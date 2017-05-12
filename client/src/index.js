import React from 'react';
import ReactDOM from 'react-dom';
import {PhotoStore, TagStore, App} from './App';
import './index.css';
import DevTools from 'mobx-react-devtools';


const photos = new PhotoStore();
const tags = new TagStore();
tags.loadSavedTags();

ReactDOM.render(
  <div>
    <App photoStore={photos} tagStore={tags}/>,
    <DevTools />
  </div>,
  document.getElementById('root')
);
