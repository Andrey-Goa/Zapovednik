import {TagStore} from '../src/App';


it('save store works good', () => {
  const tagStore = new TagStore();
  expect(tagStore.good.length).toBe(0);
  expect(tagStore.bad.length).toBe(0);
  tagStore.saveTag('x', 'good');
  expect(tagStore.good.length).toBe(1);
  expect(tagStore.tagColor('x')).toBe('good');
});