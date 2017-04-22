package ru.zapovednik.controllers;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.zapovednik.db.Tag;

/**
 * Created by andrey-goa on 22.04.17.
 */
@RestController
public class ZapovednikController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHello() throws JSONException{
        Tag t1 = new Tag();
        t1.setName("#красивыеместа");
        return new JSONObject().put("tag", t1.getName() ).toString();

    }
}
