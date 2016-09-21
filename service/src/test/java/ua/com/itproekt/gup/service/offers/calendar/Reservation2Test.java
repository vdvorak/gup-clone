package ua.com.itproekt.gup.service.offers.calendar;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reservation2Test {

    JsonObject jsonObject;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        try {
//            jsonObject = (JsonObject) parser.parse(new FileReader("service/src/test/java/ua/com/itproekt/gup/service/offers/calendar/test.json"));
            jsonObject = (JsonObject) parser.parse(new FileReader("src/test/java/ua/com/itproekt/gup/service/offers/calendar/test.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        jsonObject = null;
    }

    @Test
    public void testLoadJSON(){
        System.out.println( jsonObject.get("name").getAsString() );
    }

}
