package ua.com.itproekt.gup.service.offers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import ua.com.itproekt.gup.service.offers.price.MonthOfPrice;

/**
* @see http://www.javacreed.com/gson-deserialiser-example/
* @see http://stackoverflow.com/questions/20523693/how-to-de-serialize-a-mapstring-object-with-gson
* @see http://stackoverflow.com/questions/6980376/convert-from-days-to-milliseconds
*/

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class) // java.lang.IllegalStateException: Failed to load ApplicationContext
//@WebAppConfiguration
public class MonthOfPricesServiceTest {

    private Logger logger = Logger.getLogger(MonthOfPricesServiceTest.class);

    static String formatter = "d.MM.yyyy";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    private final String PATH = "src/test/resources"; //TODO: file.properties
    private final String RENTCALENDAR_FILE_NAME = "offerOctoberOfPrices.json"; //TODO: file.properties
    private final String RENT_FILE_NAME = "offerRents.json"; //TODO: file.properties

    private JsonObject jsonCalendars,jsonRents;
    private Map<String, MonthOfPrice> calendar1; //TODO: правилА будут хранится в базе (из низ потом будет строиться объект-календаря с ценой за все дни...)
    private Map<String, RentTest> rents; //TODO: общая таблица в базе данных для аренды...
    private OfferPricesService schemeDefault,scheme1,scheme2;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson         gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        calendar1 = gson.fromJson(jsonCalendars, new TypeToken<Map<String, MonthOfPrice>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, RentTest>>(){}.getType());

        schemeDefault = new OfferPricesServiceImpl(10000l,15000l);
        scheme1 = new OfferPricesServiceImpl(10000l,15000l);
        scheme2 = new OfferPricesServiceImpl(10000l,15000l);
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
     * Test(s) Scheme-Default
     */
    @Test
    public void testCalendarToStringDefault(){
        System.out.println("--------------------[ testCalendarToStringDefault ]");
        schemeDefault.addPrices(calendar1.get("scheme4").getPrice(), convertDate(calendar1.get("scheme4").getDays()));
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


    /**
     * Test(s) Scheme-1
     */
    @Test
    public void testScheme1(){
        System.out.println("--------------------[ testScheme1 ]");
        scheme1.addPrices(calendar1.get("scheme1").getPrice(), convertDate(calendar1.get("scheme1").getDays()));
        System.out.println(scheme1);
    }

    @Test
    public void testGetPriceScheme1(){
        System.out.println("--------------------[ testGetPriceScheme1 ]");
        scheme1.addPrices(calendar1.get("scheme2").getPrice(), convertDate(calendar1.get("scheme2").getDays()));
        scheme1.addPrices(calendar1.get("scheme3").getPrice(), convertDate(calendar1.get("scheme3").getDays()));
        System.out.println( "Day(6) Get-Price = " + scheme1.getPrice(1402002000000l) );
    }

    @Test
    public void testGetPeriodPricesScheme1(){
        System.out.println("--------------------[ testGetPeriodPricesScheme1 ]");
        scheme1.addPrices(calendar1.get("scheme2").getPrice(), convertDate(calendar1.get("scheme2").getDays()));
        scheme1.addPrices(calendar1.get("scheme3").getPrice(), convertDate(calendar1.get("scheme3").getDays()));
        System.out.println( "Period(18-25) Get-Prices = " + scheme1.getPrice(convertDate(rents.get("rent1").getDays())) );
    }


    /**
     * Test(s) Scheme-2
     */
    @Test
    public void testScheme2(){
        System.out.println("--------------------[ testScheme2 ]");
        scheme2.addPrices(calendar1.get("scheme2").getPrice(), convertDate(calendar1.get("scheme2").getDays()));
        scheme2.addPrices(calendar1.get("scheme3").getPrice(), convertDate(calendar1.get("scheme3").getDays()));
        System.out.println(scheme2);
    }


    @Test
    public void testDate() {
        System.out.println("--------------------[ testDate ]");
        String strDate = "30.06.2014";
        long lDate = 1404075600000l;
        System.out.println("<DAY.MONTH.YEAR> >> (Long): '" + convertDate(strDate) + "'");
        System.out.println("(Long) >> <DAY.MONTH.YEAR>: '" + convertDate(lDate) + "'");
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
