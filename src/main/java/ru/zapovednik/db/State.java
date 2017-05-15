package ru.zapovednik.db;

/**
 * Created by andrey-goa on 11.05.17.
 */
public enum State {
    GOOD("good", 1f),
    BAD("bad", -1f),
    NEUTRAL("", 0f);
    public final String key;
    public final float weight;

    State(String key, float weight) {
        this.key = key;
        this.weight = weight;
    }

    public static State find(String key) throws Throwable {
        return State.valueOf(key.toUpperCase());
    }
}
