import React, { Component } from 'react';
import './App.css';
import Places from './Places';


class App extends Component {

  constructor() {
    super();
    this.state = {filter: 'заброс'};

    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({filter: event.target.value});
  }

  render() {
    return (
      <div className="App">
        <p className="App-intro">
          Interesting places
        </p>
        Tag: <input type="text" defaultValue={this.state.filter} ref="filter"/> <button onClick={this.handleChange}>Find</button>
        <Places filter={this.state.filter}/>
      </div>
    );
  }
}

export default App;
