package ru.zapovednik.controllers;


import me.postaddict.instagramscraper.Instagram;
import me.postaddict.instagramscraper.exception.InstagramAuthException;
import me.postaddict.instagramscraper.exception.InstagramException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zapovednik.db.Photo;
import ru.zapovednik.db.Tag;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by andrey-goa on 22.04.17.
 */
@RestController
public class ZapovednikController {
    Instagram instagram = new Instagram();

    @PostConstruct
    public void init() throws InstagramException, InstagramAuthException, IOException {
        instagram.withCredentials("zapovednik_crawler", "startups");
        instagram.login();
        System.out.println("xxx");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public String getHello() throws JSONException{
        Tag t1 = new Tag();
        t1.setName("#красивыеместа");
        return new JSONObject(). put("tag", t1.getName() ).toString();
    }

    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    public List<Photo> getPhotos(@RequestParam("tag") String tag) throws IOException, InstagramException {
        return instagram.getMediasByTag(tag, 50).stream().map(m -> new Photo(m.caption, m.imageUrls.standard)).collect(toList());
    }
}
