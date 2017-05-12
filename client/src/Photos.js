import React from "react";
import Tags from './Tags';

import {observer} from 'mobx-react';

const Photos = observer(class Photos extends React.Component {

  render() {
    let result = this.props.photos.photos.map(photo => {
      return  <li className="photo">
        <div>
           <img src={photo.url} width={300} height={300}/>
           <Tags tags={photo.tags} store={this.props.tags} selectTag={this.props.selectTag}/>
        </div>
      </li>;
    });
    if(result.length == 0) result = <div>No photos</div>;

    return (
      <div className="container">{this.props.photos.loading ? <div> Loading ... </div> : result}</div>
    );
  }
});

export default Photos;