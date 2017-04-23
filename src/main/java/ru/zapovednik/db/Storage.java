package ru.zapovednik.db;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class Storage {

    private Preferences prefs;

    public Storage(String name) {
        prefs = Preferences.userRoot().node(name);
    }

    public float setTagWeight(String tag, float weight) {
        prefs.putFloat(tag, weight);
        return weight;
    }

    public float getTagWeight(String tag) {
        return prefs.getFloat(tag, 0f);
    }

    public Map<String, Float> getAllTagWeight() throws BackingStoreException {
        Map<String, Float> result = new HashMap<>();

        for (String key : prefs.keys()) {
            result.put(key, prefs.getFloat(key, 0f));
        }

        return result;
    }

    public enum State {
        GOOD("good", 1f),
        BAD("bad", -1f);

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
}