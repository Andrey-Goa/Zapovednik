import React, { Component } from 'react';
import './App.css';
import Photos from './Photos';


class App extends Component {

  constructor() {
    super();
    this.state = {filter: 'заброс', input: 'заброс'};

    this.applyFilter = this.applyFilter.bind(this);
    this.handleFilterInputChange = this.handleFilterInputChange.bind(this);
  }

  handleFilterInputChange(event) {
    this.setState({input: event.target.value});
  }

  applyFilter(filter) {
    this.setState({filter: filter || this.state.input});
    this.setState({input: filter || this.state.input});
  }

  render() {
    return (
      <div className="App">
        <p className="App-intro">
          Interesting places
        </p>
        Tag: <input type="text" value={this.state.input} onChange={this.handleFilterChange} /> <button onClick={() => this.applyFilter()}>Find</button>
        <Photos filter={this.state.filter} selectTag={this.applyFilter}/>
      </div>
    );
  }
}

export default App;
