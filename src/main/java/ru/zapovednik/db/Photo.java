package ru.zapovednik.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class Photo implements Comparable<Photo> {

    @JsonProperty public String url;
    @JsonProperty public List<String> tags = new ArrayList<>();
    @JsonProperty public float weight;

    public Photo(String caption, String url) {
        this.url = url;
        this.tags = Arrays.stream(StringUtils.substringAfter(caption, "#").split("#")).map(t -> StringUtils.substringBefore(t, " ")).collect(toList());
    }

    @Override
    public int compareTo(Photo photo) {
        return ((Float) photo.weight).compareTo(weight);
    }
}