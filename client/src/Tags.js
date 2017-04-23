import React from 'react';

export default class Tags extends React.Component {

  render() {
    return (
      <div className="tags">
        {this.props.value.map(tag => <p><span onClick={() => this.props.selectTag(tag)} className={this.props.colors[tag]}>#{tag}              </span>
        <span className="option" onClick={() => this.props.onGreenTag(tag)}><img src="like.png" width={20} height={20}/>                   </span>
        <span className="option" onClick={() => this.props.onRedTag(tag)}><img src="dislike.png" width={20} height={20}/></span></p>)}
      </div>
    );
  }
}
