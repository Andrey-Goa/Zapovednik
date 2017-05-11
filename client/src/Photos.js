import React from "react";
import Tags from './Tags';

import {observer} from 'mobx-react';

const Photos = observer(class Photos extends React.Component {

  constructor() {
    super();

    this.state = {tags: []};

    this.onGreenTag = this.onGreenTag.bind(this);
    this.onRedTag = this.onRedTag.bind(this);
  }

  render() {
    let result = this.props.store.photos.map(photo => {
      return  <li className="photo">
        <div>
           <img src={photo.url} width={300} height={300}/>
           <Tags value={photo.tags} colors={this.state.tags} onGreenTag={this.onGreenTag} onRedTag={this.onRedTag} selectTag={this.props.selectTag}/>
        </div>
      </li>;
    });
    if(result.length == 0) result = <div>No photos</div>;

    return (
      <div className="container">{this.props.store.loading ? <div> Loading ... </div> : result}</div>
    );
  }

  onGreenTag(tag) {
    this.saveTag(tag, 'good');
  }

  onRedTag(tag) {
    this.saveTag(tag, 'bad');
  }

  saveTag(tag, value) {
    this.state.tags[tag] = value;
    this.setState({tags: this.state.tags});
    fetch(`/setTagState?tag=${tag}&state=${value}`, {method: 'post'});
  }


});

export default Photos;