package ru.zapovednik.db;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anton on 23/04/2017.
 */
public class PhotoTest {


    @Test
    public void parseTags() {
        Photo photo = new Photo("Обуховская больница, здание заброшено #rooftop_spb#photorussia_pit#spbgram", "");
        assertEquals(3, photo.tags.size());
    }
}