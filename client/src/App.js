import React, { Component } from 'react';
import './App.css';
import Places from './Places';


class App extends Component {

  constructor() {
    super();
    this.state = {};

    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({tag: event.target.value});
  }

  render() {
    return (
      <div className="App">
        <p className="App-intro">
          Interesting places
        </p>
        Tag: <input type="text" value={this.state.tag} onChange={this.handleChange}/>
        <Places/>
      </div>
    );
  }
}

export default App;
