import React from "react";
import Tags from './Tags';

export default class Photos extends React.Component {

  constructor() {
    super();

    //const proto = {url: 'https://scontent-arn2-1.cdninstagram.com/t51.2885-15/s480x480/e35/17586803_201785576991043_2484062329268862976_n.jpg', tags: ['a', 'b', 'c']};
    this.state = {'photos': [],  tags: []};

    this.onGreenTag = this.onGreenTag.bind(this);
    this.onRedTag = this.onRedTag.bind(this);
  }

  componentDidMount() {
    this.loadData();
  }

  componentWillReceiveProps() {
    this.loadData();
  }

  loadData() {
    fetch('/photos/' + this.props.filter).then((result) => {
      return result.json();
    }).then((json) => {
      this.setState({photos: json});
    });
  }

  render() {
    const result = this.state.photos.map(photo => {
      return <li className="photo">
        <div>
           <img src={photo.url} width={300} height={300}/>
           <Tags value={photo.tags} colors={this.state.tags} onGreenTag={this.onGreenTag} onRedTag={this.onRedTag} selectTag={this.props.selectTag}/>
        </div>
      </li>;
    });

    return (
      <div className="container">{result}</div>
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
