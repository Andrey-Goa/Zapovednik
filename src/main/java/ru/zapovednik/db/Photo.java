package ru.zapovednik.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Photo implements Comparable<Photo> {

    @JsonProperty public String url;
    @JsonProperty public List<String> tags;
    @JsonProperty public float weight;

    public Photo(String caption, String url) {
        this.url = url;
        this.tags = Arrays.stream(caption.split(" ")).filter(s -> s.startsWith("#")).collect(toList());
    }

    @Override
    public int compareTo(Photo photo) {
        return ((Float) photo.weight).compareTo(weight);
    }
}