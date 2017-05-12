import React from 'react';

export default class Tags extends React.Component {

  render() {
    return (
      <div className="tags">
        {this.props.tags.map(tag => <p><span onClick={() => this.props.selectTag(tag)} className={this.props.store.tagColor(tag)}>#{tag}              </span>
        <span className="option" onClick={() => this.props.store.saveTag(tag, 'good')}><img src="like.png" width={20} height={20}/>                   </span>
        <span className="option" onClick={() => this.props.store.saveTag(tag, 'bad')}><img src="dislike.png" width={20} height={20}/></span></p>)}
      </div>
    );
  }
}
