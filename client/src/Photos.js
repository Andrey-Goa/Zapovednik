import React from "react";
import Tags from './Tags';

export default class Photos extends React.Component {

  constructor() {
    super();

    this.state = {'photos': [],  tags: []};

    this.onGreenTag = this.onGreenTag.bind(this);
    this.onRedTag = this.onRedTag.bind(this);
  }

  componentDidMount() {
    this.loadData();
  }

  componentWillReceiveProps(nextProps) {
    if(this.props.filter !== nextProps.filter) {
      this.loadData(nextProps.filter);
    }
  }


  loadData(filter) {
    filter = filter || this.props.filter;
    this.setState({photos: [], loading: true});
    fetch('photos/' + filter).then((result) => {
      return result.json();
    }).then((json) => {
      this.setState({photos: json, loading: false});
    });
  }

  render() {
    let result = this.state.photos.map(photo => {
      return  <li className="photo">
        <div>
           <img src={photo.url} width={300} height={300}/>
           <Tags value={photo.tags} colors={this.state.tags} onGreenTag={this.onGreenTag} onRedTag={this.onRedTag} selectTag={this.props.selectTag}/>
        </div>
      </li>;
    });
    if(result.length == 0) result = <div>No photos</div>;

    return (
      <div className="container">{this.state.loading ? <div> Loading ... </div> : result}</div>
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


}
