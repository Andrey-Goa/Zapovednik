import {TagStore} from '../src/App';



it('saving tags', () => {
  const tagStore = new TagStore();
  tagStore.saveTag('x', 'good');
  expect(tagStore.good.length).toBe(1);
  expect(tagStore.tagColor('x')).toBe('good');
});


it('loading tags works good', () => {
  global.fetch = jest.fn().mockImplementation(() => {
    return new Promise((resolve) => {
      resolve({
        ok: true,
        json: function() {
          return {'good': ['leia'], 'bad' : ['joker']}
        }
      });
    });
  });

  const tagStore = new TagStore();
  expect(tagStore.good.length).toBe(0);
  expect(tagStore.bad.length).toBe(0);

  return tagStore.loadSavedTags().then(() =>  {
    expect(tagStore.good[0]).toBe('leia');
    expect(tagStore.bad[0]).toBe('joker');
  });
});


