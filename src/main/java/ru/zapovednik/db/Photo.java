package ru.zapovednik.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by andrey-goa on 22.04.17.
 */
public class Photo {
    @JsonProperty public List<String> tags;
    @JsonProperty public String url;

    public Photo(String caption, String url) {
        this.tags = Arrays.asList(caption.split(" ")).stream().filter(s -> s.startsWith("#")).collect(toList());
        this.url = url;
    }
}
