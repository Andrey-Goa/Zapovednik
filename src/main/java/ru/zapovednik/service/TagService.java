package ru.zapovednik.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.zapovednik.db.State;
import ru.zapovednik.db.Tag;
import ru.zapovednik.repository.TagRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TagService{

    private State bad = State.BAD;
    private State good = State.GOOD;

    @Autowired
    private TagRepository tagRepository;

    public Iterable<Tag> all() {
        return tagRepository.findAll();
    }

    public List<Tag> allState(State state) {
        return tagRepository.findByState(state);
    }

    public  List<String> allBad() {
        List<String> bad_list = new ArrayList<>();
        List<Tag> tagbad =  allState(bad);
        for(int i=0; i< tagbad.size();i++){
            bad_list.add(tagbad.get(i).getName());
        }
        return bad_list;
    }


    public  List<String> allGood() {
        List<String> good_list = new ArrayList<>();
        List<Tag> taggood =  allState(good);
        for(int i=0; i< taggood.size();i++){
            good_list.add(taggood.get(i).getName());
        }
        return good_list;
    }

    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(Tag tag) {
        return tagRepository.save(tag);
    }

    public boolean existsByTag_name(String name){
        return tagRepository.existsByName(name);
    }

    public State getState(String name){
        if(existsByTag_name(name)){
           return tagRepository.findByName(name).getState();
        } else {
            return State.NEUTRAL;
        }
    }

    public  Tag findTag(String name){
        return tagRepository.findByName(name);
    }

}
