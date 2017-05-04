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

    private Map<String, Float> prefs = new HashMap<>();

   /* public Storage(String name) {
        prefs = Preferences.userRoot().node(name);
    }*/

    public float setTagWeight(String tag, float weight) {
        prefs.put(tag, weight);
        return weight;
    }

    public float getTagWeight(String tag) {
        if(prefs.containsKey(tag)) {
            return prefs.get(tag);
        }else{
            return 0;
        }
    }

    public Map<String, Float> getAllTagWeight() {
        return prefs;
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