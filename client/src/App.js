import React, { Component } from 'react';
import './App.css';
import Photos from './Photos';
import {extendObservable, action, autorun, whyRun} from 'mobx';
import {observer} from 'mobx-react';


class Store {
  constructor() {
    extendObservable(this, {
      filter : 'заброс',
      tags: {'good' : [], 'bad' : []},
      loading: false,
      photos : []
    });
    this.loadSavedTags();
    autorun(() => this.loadPhotos());
  }

  loadSavedTags() {
    fetch('savedTags').then((result) => {
      return result.json();
    }).then((json) => {
      this.tags = json;
    });
  }

  loadPhotos() {
      this.photos = [];
      this.loading = true;
      fetch('photos/' + this.filter).then((result) => {
        return result.json();
      }).then(action((json) => {
          this.photos = json;
          this.loading = false;
      }));
  }

}

const App = observer(class App extends Component {

  constructor() {
    super();
    this.state = {input: 'заброс'};

    this.onFindClick = this.onFindClick.bind(this);
    this.updateFilter = this.updateFilter.bind(this);
    this.handleFilterInputChange = this.handleFilterInputChange.bind(this);
  }

  handleFilterInputChange(event) {
    this.setState({input: event.target.value});
  }

  onFindClick(event) {
    if(event) event.preventDefault();
    this.updateFilter(this.state.input);
  }

  updateFilter(filter) {
    this.props.store.filter = filter;
    this.props.store.loadSavedTags();
    this.setState({input: filter});
  }


  render() {
    const store = this.props.store;
    return (
      <div className="App">
        <form>
          <div className="input-group">

            <span className="input-group-addon" id="sizing-addon1">#</span>
            <input type="text" value={this.state.input} onChange={this.handleFilterInputChange} className="form-control" aria-describedby="sizing-addon1"/>

            <span className="input-group-btn">
              <button className="btn btn-default" onClick={this.onFindClick}>Find</button>
            </span>
          </div>
        </form>
        <div>
          Filter: {store.filter}
          <div> Good: {store.tags.good.join(', ')} </div>
          <div> Bad: {store.tags.bad.join(', ')} </div>
        </div>
        <Photos  store={store} selectTag={this.updateFilter}/>
      </div>
    );

  }
});

export { Store, App };