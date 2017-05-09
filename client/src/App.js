import React, { Component } from 'react';
import './App.css';
import Photos from './Photos';


class App extends Component {

  constructor() {
    super();
    this.state = {filter: 'kransodarling', input: 'kransodarling', savedTags: {'good' : [], 'bad' : []}};

    this.onFindClick = this.onFindClick.bind(this);
    this.updateFilter = this.updateFilter.bind(this);
    this.handleFilterInputChange = this.handleFilterInputChange.bind(this);

    this.loadSavedTags();
  }

  handleFilterInputChange(event) {
    this.setState({input: event.target.value});
  }

  onFindClick(event) {
    if(event) event.preventDefault();
    this.updateFilter();
  }

  updateFilter(filter) {
    this.setState({filter: filter || this.state.input, input: filter || this.state.input});
    this.loadSavedTags();
  }


  loadSavedTags() {
    fetch('savedTags').then((result) => {
      return result.json();
    }).then((json) => {
      this.setState({savedTags: json});
    });
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
          <div> Good: {this.state.savedTags.good.join(', ')} </div>
          <div> Bad: {this.state.savedTags.bad.join(', ')} </div>
        </div>
        <Photos filter={this.state.filter} selectTag={this.updateFilter}/>
      </div>
    );

  }
}

export default App;
