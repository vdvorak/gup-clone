package ua.com.itproekt.gup.service.offers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.service.offers.price.PriceOfRent;
import ua.com.itproekt.gup.util.ConvertUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * @see http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/LinkedBlockingDeque.html
 */
public class OfferPricesServiceToGsonTest {

    private final String PATH = "src/test/resources",
            RENTCALENDAR_FILE_NAME = "offerOctoberOfPrices.json",
            RESTORECALENDAR_FILE_NAME = "restoreMonthOfPrices.json",
            RENT_FILE_NAME = "offerRents.json", //FIXME: file.properties
            jsonRestore = "{\n" +
                    "  \"monthOfPrices\": {\n" +
                    "    \"weekday\": {\n" +
                    "      \"price\": 10000\n" +
                    "      ,\"days\": [\"1.11.2016\",\"30.11.2016\"]\n" +
                    "    }\n" +
                    "    ,\"weekend\": {\n" +
                    "      \"price\": 15000\n" +
                    "      ,\"days\": [\"12.11.2016\",\"27.11.2016\"]\n" +
                    "    }\n" +
                    "    ,\"specialdays\": [\n" +
                    "      {\n" +
                    "        \"price\": 20000\n" +
                    "        ,\"days\": [\"3.11.2016\",\"7.11.2016\"]\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}",
            jsonRestore2 = "{\n" +
                    "  \"rents\": {\n" +
                    "    \"availables\": [\n" +
                    "      {\"day\": \"1.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"2.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"3.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"4.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"5.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"6.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"7.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"8.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"9.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"10.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"11.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"12.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"13.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"14.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"15.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"16.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"17.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"18.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"19.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"20.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"21.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"22.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"23.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"24.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"25.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"28.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"30.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  ,\"rented\": [\n" +
                    "      {\"day\": \"26.11.2016\", \"user\": {\"id\": \"57e440464c8eda79f765532d\", \"fullName\": \"Петренко Юрий Владимирович\", \"imgId\": \"57e440464c8eda79f765532d\", \"rating\": null}, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": \"10.11.2016\", \"updateDate\": \"10.11.2016\", \"rentStatus\": \"RENTED\", \"orderStatus\": \"SUCCESSFULLY_ORDER\", \"salesRemained\": \"0\", \"order\": null\n" +
                    "      },{\"day\": \"27.11.2016\", \"user\": {\"id\": \"57e440464c8eda79f765532d\", \"fullName\": \"Петренко Юрий Владимирович\", \"imgId\": \"57e440464c8eda79f765532d\", \"rating\": null}, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": \"10.11.2016\", \"updateDate\": \"10.11.2016\", \"rentStatus\": \"RENTED\", \"orderStatus\": \"SUCCESSFULLY_ORDER\", \"salesRemained\": \"0\", \"order\": null\n" +
                    "      },{\"day\": \"29.11.2016\", \"user\": {\"id\": \"57e440464c8eda79f765532d\", \"fullName\": \"Петренко Юрий Владимирович\", \"imgId\": \"57e440464c8eda79f765532d\", \"rating\": null}, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": \"10.11.2016\", \"updateDate\": \"10.11.2016\", \"rentStatus\": \"RENTED\", \"orderStatus\": \"SUCCESSFULLY_ORDER\", \"salesRemained\": \"0\", \"order\": null\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  ,\"expired\": [\n" +
                    "      {\"day\": \"1.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"2.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"3.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"4.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"5.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"6.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"7.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"8.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"9.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      },{\"day\": \"10.11.2016\", \"user\": null, \"confirm\": true, \"prepaid\": true, \"dayPrepaid\": null, \"orderDate\": null, \"updateDate\": null, \"rentStatus\": \"AVAILABLE\", \"orderStatus\": \"NONE\", \"salesRemained\": \"1\", \"order\": null\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";

    private JsonObject jsonPriceRents,jsonRents;
    private Map<String, PriceOfRent> priceRents; //TODO: ПравилА будут хранится в базе (из низ потом будет строиться объект-календаря с ценой за все дни...)
    private Map<String, RentTest> rents;         //TODO: информация об состоянии аренды для клиентов...будут хранится в базе
    private OfferPricesService service;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonPriceRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        priceRents = gson.fromJson(jsonPriceRents, new TypeToken<Map<String, PriceOfRent>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, RentTest>>(){}.getType());

        service = new OfferPricesServiceImpl(10000l,15000l); // Устанавливаем цену по умолчанию (на будни и выходные дни)
    }

    @After
    public void tearDown() {
        jsonPriceRents = null;
        jsonRents = null;
        priceRents = null;
        rents = null;
        service = null;
    }

//    @Test
//    public void testContains(){
//        System.out.println("--------------------[ testContains (Rent) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d");
//        service.addRent(ConvertUtil.toDate(rents.get("rent52").getDays()), "57e440464c8eda79f765532d");
//
//        System.out.println(service.toJson());
//        System.out.println(service);
//        System.err.println(service.toRent());
//        System.out.println(service.jsonRent());
//
////        System.out.println( Arrays.toString( service.toArray() ) );
//    }
//
//    @Test
//    public void testAdd(){
//        System.out.println("--------------------[ testAdd (Rents) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d");
//        service.addRent(ConvertUtil.toDate(rents.get("rent52").getDays()), "57e440464c8eda79f765532d");
//
//        System.out.println(service.toJson());
////        System.out.println(service.jsonRent());
//////        System.out.println(service.jsonOfferMonth());
//    }
//
//
//
//
//
//
//
//    @Test
//    public void testInit(){
//        System.out.println("--------------------[ testInit (Availables) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//
//        System.out.println(service.toRent());
//    }
//
//    @Test
//    public void testRented(){
//        System.out.println("--------------------[ testRented (s) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d");
//        service.addRent(ConvertUtil.toDate(rents.get("rent52").getDays()), "57e440464c8eda79f765532d");
//
//        System.out.println(service.toRent());
//    }
//
//    @Test
//    public void testIsRented(){
//        System.out.println("--------------------[ testIsRented ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d");
//        service.addRent(ConvertUtil.toDate(rents.get("rent52").getDays()), "57e440464c8eda79f765532d");
//
//        System.out.println("IsRented [05.11.2016] = " + service.isRent(ConvertUtil.toDate(rents.get("delete41").getDays())[0]) + " (false)");
//        System.out.println("IsRented [16.11.2016,31.11.2016] = " + service.isRent(ConvertUtil.toDate(rents.get("notfound").getDays())) + " (false)");
//        System.out.println("IsRented [26.11.2016] = " + service.isRent(ConvertUtil.toDate(rents.get("rent51").getDays())[0]) + " (true)");
//        System.out.println("IsRented [28.11.2016,30.11.2016] = " + service.isRent(ConvertUtil.toDate(rents.get("rent52").getDays())) + " (true)");
//        System.out.println(service.toRent());
//    }
//
//    @Test
//    public void testDel0(){
//        System.out.println("--------------------[ testDel (0) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("delete41").getDays()), "57e440464c8eda79f765532d");
//        service.addRent(ConvertUtil.toDate(rents.get("delete42").getDays()), "57e440464c8eda79f765532d");
//
//        System.out.println("Del [1.11.2016] = " + service.delRent(ConvertUtil.toDate(rents.get("rent41").getDays()))); //FIXME: проблеа конвертара с невалидными числами в месяцк...
//        System.out.println(service.toRent());
//    }
//
//    @Test
//    public void testDel1(){
//        System.out.println("--------------------[ testDel (1) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d");
//        service.addRent(ConvertUtil.toDate(rents.get("delete42").getDays()), "57e440464c8eda79f765532d");
//
//        System.out.println("Del [26.11.2016] = " + service.delRent(ConvertUtil.toDate(rents.get("rent51").getDays())));
//        System.out.println(service.toRent());
//    }
//
//    @Test
//    public void testDel2(){
//        System.out.println("--------------------[ testDel (2) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d");
//        service.addRent(ConvertUtil.toDate(rents.get("delete42").getDays()), "57e440464c8eda79f765532d");
//
//        System.out.println("Del [12.11.2016,14.11.2016] = " + service.delRent(ConvertUtil.toDate(rents.get("delete42").getDays())));
//        System.out.println(service.toRent());
//    }
//
//    @Test
//    public void testAddDelAdd(){
//        System.out.println("--------------------[ testAddDelAdd ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d");   //[+] 26.11.2016
//        service.addRent(ConvertUtil.toDate(rents.get("delete42").getDays()), "57e440464c8eda79f765532d"); //[+] 12.11.2016,14.11.2016
//
//        service.delRent(ConvertUtil.toDate(rents.get("delete42").getDays())); //[-] 12.11.2016,14.11.2016
//        service.addRent(ConvertUtil.toDate(rents.get("rent4").getDays()), "57e440464c8eda79f765532d");    //[+] 30.11.2016
//
//        System.out.println(service.toRent());                                 //[=] 26.11.2016,30.11.2016
//    }
//
//
//
//
//
//
//
//    /**
//     * @see https://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/ArrayUtils.html
//     */
//    @Test
//    public void testExpired(){
//        System.out.println("--------------------[ testExpired ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d"); //[+] 26.11.2016
//        service.addRent(ConvertUtil.toDate(rents.get("rent52").getDays()), "57e440464c8eda79f765532d"); //[+] 27.11.2016,29.11.2016
//        //
//        service.delRent(ConvertUtil.toDate(rents.get("rent52").getDays())); //[-] 27.11.2016,29.11.2016
//        service.addRent(ConvertUtil.toDate(rents.get("rent4").getDays()), "57e440464c8eda79f765532d");  //[+] 30.11.2016
//        // 21.11.2016
//        System.out.println( "Availables: " + service.getRents().getAvailables() ); //[-] 26.11.2016,30.11.2016
//        System.out.println( "    Rented: " + service.getRents().getRented() );     //[+] 26.11.2016,30.11.2016
//        System.err.println("   Expired: " + service.getRents().getExpired());      //[=] 01.11.2016,...,08.11.2016
//        System.err.println("   Expired: " + service.getRents().getExpired());      //[=] 01.11.2016,...,08.11.2016
////        System.err.println( "Availables: " + service.getRents().getAvailables() ); //[-] 26.11.2016,30.11.2016
////        System.err.println( "    Rented: " + service.getRents().getRented() );     //[+] 26.11.2016,30.11.2016
//        System.out.println();
//        System.out.println(service.toRent());                                      //[=] 30.11.2016
//        System.out.println();
//        System.err.println(service);
//    }
//
//    @Test
//    public void testExpired2(){
//        System.out.println("--------------------[ testExpired (2) ]");
//        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays()), "57e440464c8eda79f765532d"); //[+] 26.11.2016
//        service.addRent(ConvertUtil.toDate(rents.get("rent52").getDays()), "57e440464c8eda79f765532d"); //[+] 28.11.2016,30.11.2016
//        //
//        service.delRent(ConvertUtil.toDate(rents.get("rent52").getDays())); //[-] 28.11.2016,30.11.2016
//        service.addRent(ConvertUtil.toDate(rents.get("rent4").getDays()), "57e440464c8eda79f765532d");  //[+] 31.11.2016
//        // 21.10.2016
//        System.out.println( "   Expired: " + service.getRents().getExpired() );
//        System.out.println( "Availables: " + service.getRents().getAvailables() );
//        System.out.println( "    Rented: " + service.getRents().getRented() );
//        System.out.println();
//        System.out.println(service.toJson());
//        System.out.println();
//        System.out.println(service.jsonRent());
//    }

//////    @Test
//////    public void testRestore(){
//////        System.out.println("--------------------[ testRestore ]");
//////        service.addPrices(priceRents.get("scheme4").getPrice(), ConvertUtil.toDate(priceRents.get("scheme4").getDays()));
//////        service.addRent(ConvertUtil.toDate(rents.get("rent51").getDays())); //[+] 26.10.2016
//////        service.addRent(ConvertUtil.toDate(rents.get("rent52").getDays())); //[+] 28.10.2016,30.10.2016
//////        //
//////        System.err.println(service.toJson());
//////        System.err.println();
//////        System.err.println(service.jsonRent());
//////    }

    @Test
    public void testRestore(){
        System.out.println("--------------------[ testRestore ]");

//        service = new OfferPricesServiceImpl(jsonRestore);
        service = new OfferPricesServiceImpl(jsonRestore, jsonRestore2);
        System.err.println(service);
        System.err.println();
        System.err.println(service.toJson());
        System.err.println();
        System.err.println(service.jsonRent());
    }

}
