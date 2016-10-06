package ua.com.itproekt.gup.server.api.rest.offers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.CalendarStatusService;
import ua.com.itproekt.gup.service.offers.CalendarStatusServiceImpl;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.offers.calendar.Calendar;
import ua.com.itproekt.gup.service.offers.calendar.Rent;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferCalendarRestController {

    private static final String formatter = "d.MM.yyyy";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    private final String PATH = "src/test/resources",
            RENTCALENDAR_FILE_NAME = "offerCalendar1.json",
            RENT_FILE_NAME = "offerRents.json";

    private JsonObject jsonCalendars,jsonRents;
    private Gson gsonStatusCalendarDefault,gsonStatusCalendar1;
    private Map<String, Calendar> calendarPrices; //TODO: правилА будут хранится в базе (из низ потом будет строиться объект-календаря с ценой за все дни...)
    private Map<String, Rent> rents; //TODO: общая таблица в базе данных для аренды...
    private CalendarStatusService statusCalendar = new CalendarStatusServiceImpl(10000l,15000l);

    @Autowired
    OffersService offersService;

    @Autowired
    ActivityFeedService activityFeedService;

    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCalendar(@PathVariable String offerId){
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());
        gsonStatusCalendarDefault = new Gson();
        gsonStatusCalendar1 = new Gson();

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        Offer offer = offersService.findById(offerId);
//        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(gsonStatusCalendarDefault.toJson(statusCalendar), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCalendar(@PathVariable String offerId,
                                                 @RequestBody Long price,
                                                 @RequestBody String firstDay,
                                                 @RequestBody String lastDay){
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());
        gsonStatusCalendarDefault = new Gson();
        gsonStatusCalendar1 = new Gson();

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        Offer offer = offersService.findById(offerId);
//        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        statusCalendar.addPrices(calendarPrices.get("scheme6").getPrice(), convertDate(calendarPrices.get("scheme6").getDays()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/calendar", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCalendar(@PathVariable String offerId,
                                                 @RequestBody String firstDay,
                                                 @RequestBody String lastDay){
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());
        gsonStatusCalendarDefault = new Gson();
        gsonStatusCalendar1 = new Gson();

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        Offer offer = offersService.findById(offerId);
//        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        statusCalendar.addPrices(calendarPrices.get("scheme5").getPrice(), convertDate(calendarPrices.get("scheme5").getDays()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/calendar", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCalendar(@PathVariable String offerId,
                                                 @RequestBody String firstDay,
                                                 @RequestBody String lastDay){
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());
        gsonStatusCalendarDefault = new Gson();
        gsonStatusCalendar1 = new Gson();

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        Offer offer = offersService.findById(offerId);
//        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(String.valueOf(statusCalendar.delPrices(convertDate(rents.get("delete42").getDays()))), HttpStatus.OK);
    }

    private Long convertDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }

    private static String convertDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    private Long[] convertDate(String[] strDate) {
        Long[] longDate = new Long[strDate.length];
        for (int date=0; date<strDate.length; ++date){
            longDate[date] = convertDate(strDate[date]);
        }
        return longDate;
    }
}
