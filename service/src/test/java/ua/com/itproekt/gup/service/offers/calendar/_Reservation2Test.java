//package ua.com.itproekt.gup.service.offers.calendar;
//
//import com.google.gson.*;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//
//public class Reservation2Test {
//
//    private JsonObject jsonReservation;
//    private final String PATH = "src/test/java/ua/com/itproekt/gup/service/offers/calendar";
//    private final String FILE_NAME = "reservations.json";
//
//
//    @Before
//    public void setUp() {
//        JsonParser parser = new JsonParser();
//        try {
////            jsonReservation = (JsonObject) parser.parse(new FileReader("service/src/test/java/ua/com/itproekt/gup/service/offers/calendar/reservations.json"));
//            jsonReservation = (JsonObject) parser.parse(new FileReader(PATH + "/" + FILE_NAME));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @After
//    public void tearDown() {
//        jsonReservation = null;
//    }
//
////    @Test
////    public void testLoad1JSON() {
//////        System.out.println("price: " + jsonReservation.get("price").getAsString());
////        System.out.println("price: " + jsonReservation.get("price").getAsInt());
////
////        System.out.print("days: ");
////        JsonArray jsonArray = jsonReservation.get("days").getAsJsonArray();
//////        for (JsonElement je : jsonArray) System.out.print(je.getAsJsonObject().get("day").getAsString() + " ");
////        for (JsonElement je : jsonArray) System.out.print(je.getAsInt() + " ");
////    }
//
//    @Test
//    public void testLoad2JSON() {
//        Gson gson = new Gson();
//        ObjReservation objReservation = gson.fromJson( jsonReservation, ObjReservation.class );
//
//        System.out.println("price: " + objReservation.getPrice());
//        for (int day : objReservation.getDays()) System.out.print(day + " ");
//    }
//
//}
//
//
//class ObjReservation {
//
//    private int price;
//    private int[] days;
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public int[] getDays() {
//        return days;
//    }
//
//    public void setDays(int[] days) {
//        this.days = days;
//    }
//
//}
