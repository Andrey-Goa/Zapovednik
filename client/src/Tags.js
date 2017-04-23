import React from 'react';

export default class Tags extends React.Component {

  render() {
    return (
      <div className="tags">{this.props.value.map(tag => <p><span className={this.props.colors[tag]}>#{tag}              </span>
        <span className="option" onClick={() => this.props.onGreenTag(tag)}>Good                   </span>
        <span className="option" onClick={() => this.props.onRedTag(tag)}>Bad</span></p>)}
      </div>
    );
  }
}
