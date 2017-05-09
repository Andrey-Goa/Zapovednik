import React, { Component } from 'react';
import './App.css';
import Photos from './Photos';


class App extends Component {

  constructor() {
    super();
    this.state = {filter: 'kransodarling', input: 'kransodarling'};

    this.applyFilter = this.applyFilter.bind(this);
    this.handleFilterInputChange = this.handleFilterInputChange.bind(this);
  }

  handleFilterInputChange(event) {
    this.setState({input: event.target.value});
  }

  applyFilter(filter) {
    let value = (filter || this.state.input);
    console.log(value);
    this.setState({filter: value, input: value});
  }

  render() {
    return (
      <div className="App">
        <div className="input-group">
          <span className="input-group-addon" id="sizing-addon1">#</span>
          <input type="text" value={this.state.input} onChange={this.handleFilterInputChange} className="form-control" aria-describedby="sizing-addon1"/>
          <span className="input-group-btn">
            <button className="btn btn-default" onClick={() => this.applyFilter()}>Find</button>
           </span>

        </div>
        <Photos filter={this.state.filter} selectTag={this.applyFilter}/>
      </div>
    );

  }
}

export default App;
