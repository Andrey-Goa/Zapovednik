package ru.zapovednik.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zapovednik.db.Tag;

/**
 * Created by andrey-goa on 22.04.17.
 */
public interface TagRepository extends CrudRepository<Tag, Integer> {

}
