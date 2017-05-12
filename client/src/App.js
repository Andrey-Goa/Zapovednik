import React, { Component } from 'react';
import './App.css';
import Photos from './Photos';
import {extendObservable, action, autorun, whyRun} from 'mobx';
import {observer} from 'mobx-react';


class PhotoStore {
  constructor() {
    extendObservable(this, {
      filter : 'заброс',
      tags: {'good' : [], 'bad' : []},
      loading: false,
      photos : []
    });
    autorun(() => this.loadPhotos());
  }


  loadPhotos() {
      this.photos = [];
      this.loading = true;
      fetch('photos/' + this.filter).then((result) => {
        return result.json();
      }).then(action((json) => {
          this.photos = json;
          this.loading = false;
      }));
  }
}

class TagStore {
  constructor() {
    extendObservable(this, {
      good: [],
      bad: []
    });
    this.loadSavedTags();
  }

  loadSavedTags() {
    fetch('savedTags').then((result) => {
      return result.json();
    }).then((json) => {
      this.good = json.good;
      this.bad = json.bad;
    });
  }

  tagColor(tag) {
    if(this.good.includes(tag)) return 'good';
    else if(this.bad.includes[tag]) return 'bad';
    return 'none';
   }

  saveTag(tag, value) {
    if(value === 'good') this.good.push(tag);
    if(value === 'bad') this.bad.push(tag);
    fetch(`/setTagState?tag=${tag}&state=${value}`, {method: 'post'});
  }

}

const App = observer(class App extends Component {

  constructor() {
    super();
    this.state = {input: 'заброс'};

    this.onFindClick = this.onFindClick.bind(this);
    this.updateFilter = this.updateFilter.bind(this);
    this.handleFilterInputChange = this.handleFilterInputChange.bind(this);
  }

  handleFilterInputChange(event) {
    this.setState({input: event.target.value});
  }

  onFindClick(event) {
    if(event) event.preventDefault();
    this.updateFilter(this.state.input);
  }

  updateFilter(filter) {
    this.props.store.filter = filter;
    this.props.store.loadSavedTags();
    this.setState({input: filter});
  }


  render() {
    const photos = this.props.photoStore;
    const tags = this.props.tagStore;
    return (
      <div className="App">
        <form>
          <div className="input-group">

            <span className="input-group-addon" id="sizing-addon1">#</span>
            <input type="text" value={this.state.input} onChange={this.handleFilterInputChange} className="form-control" aria-describedby="sizing-addon1"/>

            <span className="input-group-btn">
              <button className="btn btn-default" onClick={this.onFindClick}>Find</button>
            </span>
          </div>
        </form>
        <div>
          Filter: {photos.filter}
          <div> Good: {tags.good.join(', ')} </div>
          <div> Bad: {tags.bad.join(', ')} </div>
        </div>
        <Photos photos={photos} tags={tags} selectTag={this.updateFilter}/>
      </div>
    );

  }
});

export { PhotoStore, App, TagStore };