import React, { Component } from 'react';
import './App.css';
import Photos from './Photos';
import {extendObservable} from 'mobx';
import {observer} from 'mobx-react';


class Store {
  constructor() {
    extendObservable(this, {
      filter : null,
      tags: {'good' : [], 'bad' : []},
      photos : {}
    });
    this.loadSavedTags();
  }

  loadSavedTags() {
    fetch('savedTags').then((result) => {
      return result.json();
    }).then((json) => {
      this.tags = json;
    });
  }

}

const App = observer(class App extends Component {

  constructor() {
    super();
    this.state = {input: 'kransodarling'};

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
    this.setState({input: filter || this.state.input});
    this.props.store.loadSavedTags();
  }


  render() {
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
          <div> Good: {this.props.store.tags.good.join(', ')} </div>
          <div> Bad: {this.props.store.tags.bad.join(', ')} </div>
        </div>
        <Photos filter={this.props.store.filter} selectTag={this.updateFilter}/>
      </div>
    );

  }
});

export { Store, App };