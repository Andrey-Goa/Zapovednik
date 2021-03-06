package ru.zapovednik.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.*;

public class Photo implements Comparable<Photo> {

    @JsonProperty public String instagram;
    @JsonProperty public String url;
    @JsonProperty public List<String> tags = new ArrayList<>();
    @JsonProperty public float weight;


    public Photo(String instagram, String caption, String url) {
        this.instagram = instagram;
        this.url = url;
        this.tags = Arrays.stream(substringAfter(caption, "#").split("#")).map(t -> substringBefore(t, " ")).collect(toList());
        this.tags = this.tags.size() > 10 ? this.tags.subList(0, 10) : this.tags;

    }

    @Override
    public int compareTo(Photo photo) {
        return ((Float) photo.weight).compareTo(weight);
    }
}