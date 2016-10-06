package ua.com.itproekt.gup.service.offers.calendar;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.itproekt.gup.model.offer.paidservices.Marked;

public class CalendarCreateTest {

    static String formatter = "d.MM.yyyy";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    private final String PATH = "src/test/resources"; //TODO: file.properties
    private final String RESERVATION_FILE_NAME = "offerCalendar1.json"; //TODO: file.properties
    private final String RENT_FILE_NAME = "offerRents.json"; //TODO: file.properties

    private JsonObject jsonCalendars,jsonRents;
    private Map<String, Calendar> calendar1; //TODO: правилА будут хранится в базе (из низ потом будет строиться объект-календаря с ценой за все дни...)
    private Map<String, Rent> rents; //TODO: общая таблица в базе данных для аренды...
    private CalendarStatus schemeDefault,scheme1,scheme2;


    /**
     * Test(s) Scheme-Default
     */
    @Test
    public void testCalendarToStringDefault(){
        System.out.println("--------------------[ testCalendarToStringDefault ]");
        System.out.println(schemeDefault);
    }

    @Test
    public void testCalendarIsDayDefault(){
        System.out.println("--------------------[ testCalendarIsDayDefault ]");
        System.out.println( "Is Calendar Day-Price(6): " + schemeDefault.isPrice(1402002000000l) );
    }

    @Test
    public void testGetPricesDefault(){
        System.out.println("--------------------[ testGetPricesDefault ]");
        System.out.println( "Days(1,3,7) Get-Prices = " + schemeDefault.getPrice(convertDate(rents.get("rent2").getDays())) );
    }


    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson         gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RESERVATION_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        calendar1 = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());

        schemeDefault = new CalendarStatusOwner(10000l,15000l);
        scheme1 = new CalendarStatusOwner(10000l,15000l);
        scheme2 = new CalendarStatusOwner(10000l,15000l);
    }

    @After
    public void tearDown() {
        jsonCalendars = null;
        jsonRents = null;
        calendar1 = null;
        rents = null;
        schemeDefault = null;
        scheme1 = null;
        scheme2 = null;
    }


    /**
     * <DAY.MONTH.YEAR> >> (Long)
     * ("30.06.2014" >> 1472936400000)
     *
     * @param strDate
     * @return
     */
    private Long convertDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }

    /**
     * (Long) >> <DAY.MONTH.YEAR>
     * 1472936400000 >> "30.06.2014"
     *
     * @param lDate
     * @return
     */
    private static String convertDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    /**
     * @param strDate
     * @return
     */
    private Long[] convertDate(String[] strDate) {
        Long[] longDate = new Long[strDate.length];
        for (int date=0; date<strDate.length; ++date){
            longDate[date] = convertDate(strDate[date]);
        }
        return longDate;
    }

}
