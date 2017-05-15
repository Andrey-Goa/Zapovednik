package ru.zapovednik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zapovednik.db.State;
import ru.zapovednik.db.Tag;

import java.util.List;

/**
 * Created by andrey-goa on 11.05.17.
 */

@Transactional
public interface TagRepository extends CrudRepository<Tag, Integer> {
   List<Tag> findByState(State state);
   Tag findByName(String name);
   boolean existsByName(String name);

}
