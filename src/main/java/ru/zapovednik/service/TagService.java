package ru.zapovednik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zapovednik.db.Tag;
import ru.zapovednik.repository.TagRepository;

/**
 * Created by andrey-goa on 22.04.17.
 */
@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public Iterable<Tag> all() {
        return tagRepository.findAll();
    }

    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag find(int tagId) {
        return tagRepository.findOne(tagId);
    }

}
