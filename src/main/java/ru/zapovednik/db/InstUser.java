package ru.zapovednik.db;

import java.util.List;

/**
 * Created by andrey-goa on 22.04.17.
 */
public class InstUser {
   private Integer id;
   private List<Tag> tags;

    public Integer getId() {
        return id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
