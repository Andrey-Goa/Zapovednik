package ru.zapovednik.db;

public class WeightUpdater {

    private Storage storage;

    public WeightUpdater(Storage storage) {
        this.storage = storage;
    }

    public void update(Photo photo) {
        photo.weight = photo.tags.stream().map(tag -> storage.getTagWeight(tag)).reduce((r, v) -> r += v).orElse(0f);
    }
}