package ru.zapovednik.controllers;


import me.postaddict.instagramscraper.Instagram;
import me.postaddict.instagramscraper.exception.InstagramAuthException;
import me.postaddict.instagramscraper.exception.InstagramException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zapovednik.db.Photo;
import ru.zapovednik.db.Storage;
import ru.zapovednik.db.WeightUpdater;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class ZapovednikController {

    Instagram instagram = new Instagram();

    Storage storage = new Storage();

    @PostConstruct
    public void init() throws InstagramException, InstagramAuthException, IOException {
        instagram.withCredentials("x.corp.dm@gmail.com", "#139Enter");
        instagram.login();
    }

    @RequestMapping(value = "/photos/{tag}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Photo> getPhotos(@PathVariable("tag") String tag) throws IOException, InstagramException, InstagramAuthException {
        WeightUpdater weightUpdater = new WeightUpdater(storage);

        List<Photo> result = instagram.getMediasByTag(tag, 50).stream().filter(m -> m.caption != null)
                .map(m -> new Photo(m.link, m.caption, m.imageUrls.standard)).collect(toList());

        result.forEach(weightUpdater::update);
        result.sort(Photo::compareTo);
        return result;
    }

    @RequestMapping(value = "/setTagState", params = {"tag", "state"}, method = RequestMethod.POST)
    public void setTagState(@RequestParam("tag") String tag, @RequestParam("state") String state) throws Throwable {
        storage.setTagWeight(tag, Storage.State.find(state).weight);
    }

    @RequestMapping(value = "/setTagWeight", params = {"tag", "weight"}, method = RequestMethod.POST)
    public void setTagWeight(@RequestParam("tag") String tag, @RequestParam("weight") float weight) throws Throwable {
        storage.setTagWeight(tag, weight);
    }

    @RequestMapping(value = "/savedTags", method = RequestMethod.GET)
    public Map<String, Object> getAllTagWeight() throws Throwable {
        Predicate<Map.Entry<String, Float>> condition = e -> e.getValue() >= 0.0;
        HashMap<String, Object> result = new HashMap<>();
        result.put("good", filter(condition));
        result.put("bad", filter(condition.negate()));
        return result;
    }

    private List<String> filter(Predicate<Map.Entry<String, Float>> condition) {
        return storage.getAllTagWeight().entrySet().stream().filter(condition).map(Map.Entry::getKey).collect(toList());
    }
}
