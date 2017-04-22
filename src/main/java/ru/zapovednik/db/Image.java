package ru.zapovednik.db;

import java.util.List;

/**
 * Created by andrey-goa on 22.04.17.
 */
public class Image {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private List<Tag> tags;
    private boolean like;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public boolean getLike() {
        return like;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
