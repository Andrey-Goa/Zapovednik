import React from 'react';

export default class Tags extends React.Component {

  render() {
    return (
      <div>{this.props.value.map(tag => <p><span className={this.props.colors[tag]}>#{tag}              </span>
        <span onClick={() => this.props.onGreenTag(tag)}>Good                   </span>
        <span onClick={() => this.props.onRedTag(tag)}>Bad</span></p>)}
      </div>
    );
  }
}
