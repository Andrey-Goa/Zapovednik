package ru.zapovednik.controllers;


import me.postaddict.instagramscraper.Instagram;
import me.postaddict.instagramscraper.exception.InstagramAuthException;
import me.postaddict.instagramscraper.exception.InstagramException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zapovednik.db.*;
import ru.zapovednik.service.TagService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class ZapovednikController {

    Instagram instagram = new Instagram();

    @Autowired
    private TagService tagService;


    @PostConstruct
    public void init() throws InstagramException, InstagramAuthException, IOException {
        instagram.withCredentials("x.corp.dm@gmail.com", "#139Enter");
        instagram.login();
    }

    @RequestMapping(value = "/photos/{tag}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Photo> getPhotos(@PathVariable("tag") String tag) throws IOException, InstagramException, InstagramAuthException {

        List<Photo> result = instagram.getMediasByTag(tag, 50).stream().filter(m -> m.caption != null)
                .map(m -> new Photo(m.link, m.caption, m.imageUrls.standard)).collect(toList());

        Iterator iterator = result.iterator();
        while (iterator.hasNext()){
            update((Photo) iterator.next());
        }
        result.sort(Photo::compareTo);
        return result;
    }


  @RequestMapping(value = "/setTagState", params = {"tag", "state"}, method = RequestMethod.POST)
    public void setTagState(@RequestParam("tag") String tag, @RequestParam("state") String state) throws Throwable {
        Tag newTag = new Tag();
        newTag.setName(tag);
        newTag.setState(State.find(state));
        tagService.create(newTag);
    }


   @RequestMapping(value = "/savedTags", method = RequestMethod.GET)
    public Map<String, Object> getAllTagWeight() throws Throwable {
        HashMap<String, Object> result = new HashMap<>();
        List<String> good_tag = tagService.allGood();
        List<String> bad_tag = tagService.allBad();
        result.put("good", good_tag);
        result.put("bad", bad_tag);
        return result;
    }


    public void update(Photo photo) {
        photo.weight = photo.tags.stream().map(tag -> tagService.getState(tag).weight).reduce((r, v) -> r += v).orElse(0f);

    }



}