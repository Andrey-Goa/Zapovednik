package ru.zapovednik.controllers;


import me.postaddict.instagramscraper.Instagram;
import me.postaddict.instagramscraper.exception.InstagramAuthException;
import me.postaddict.instagramscraper.exception.InstagramException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.zapovednik.db.Photo;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by andrey-goa on 22.04.17.
 */
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class ZapovednikController {
    Instagram instagram = new Instagram();

    @PostConstruct
    public void init() throws InstagramException, InstagramAuthException, IOException {
        instagram.withCredentials("x.corp.dm@gmail.com", "startups");
        instagram.login();
    }

    @RequestMapping(value = "/photos/{tag}", method = RequestMethod.GET)
    public List<Photo> getPhotos(@PathVariable("tag") String tag) throws IOException, InstagramException, InstagramAuthException {
        return instagram.getMediasByTag(tag, 50).stream().filter(m -> m.caption != null)
                        .map(m -> new Photo(m.caption, m.imageUrls.standard)).collect(toList());
    }
}
