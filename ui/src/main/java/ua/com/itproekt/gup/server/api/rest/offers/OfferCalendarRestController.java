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
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.CalendarStatusService;
import ua.com.itproekt.gup.service.offers.CalendarStatusServiceImpl;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.offers.calendar.CalendarPrice;
import ua.com.itproekt.gup.service.offers.calendar.CalendarPriceObj;
import ua.com.itproekt.gup.service.offers.calendar.Rent;

import java.io.*;
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

    private final String PATH = "D:/IdeaProjects/GUP_auto_merge/gup/ui/src/main/resources",
            offerCalendar1 = "offerCalendar1.json",
            offerRents = "offerRents.json";

    private JsonObject jsonCalendars,jsonRents;
    private Map<String, CalendarPrice> calendarPrices;
    private Map<String, Rent> rents;

//    @Autowired
    private CalendarStatusService calendarStatusService;

    @Autowired
    private OffersService offersService;

    @Autowired
    private ActivityFeedService activityFeedService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCalendar(@PathVariable String offerId){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        JsonParser       parser = new JsonParser();
        ClassLoader classLoader = getClass().getClassLoader();
        Gson               gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(classLoader.getResource(offerCalendar1).getFile())); //jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + offerCalendar1));
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, CalendarPrice>>(){}.getType());

//        calendarStatusService = new CalendarStatusServiceImpl();
        calendarStatusService = new CalendarStatusServiceImpl(10000l,15000l); // Устанавливаем цену по умолчанию (на будни и выходные дни)
        calendarStatusService.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays())); // Устанавливаем специальную цену на отдельные дни

        return new ResponseEntity<>(calendarStatusService.toJson(), HttpStatus.OK); //return new ResponseEntity<>(calendarStatusService.toJson(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCalendar(@PathVariable String offerId,
                                                 @RequestBody CalendarPriceObj calendarPrice){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (calendarPrice.getWeekdayPrice()==null
                && calendarPrice.getWeekendPrice()==null
                && calendarPrice.getSpecialPrice()==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        calendarStatusService = new CalendarStatusServiceImpl(calendarPrice.getWeekdayPrice(),calendarPrice.getWeekendPrice()); // Устанавливаем дефолтную цену (на будни и выходные дни)
        calendarStatusService.addPrices(calendarPrice.getSpecialPrice().getPrice(), convertDate(calendarPrice.getSpecialPrice().getDays())); // Устанавливаем специальную цену на отдельные дни
        return new ResponseEntity<>(calendarStatusService.toJson(), HttpStatus.CREATED); //return new ResponseEntity<>(calendarPrice.toString(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCalendar(@PathVariable String offerId,
                                                 @RequestBody CalendarPriceObj calendarPrice){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (calendarPrice.getWeekdayPrice()==null
                && calendarPrice.getWeekendPrice()==null
                && calendarPrice.getSpecialPrice()==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        JsonParser       parser = new JsonParser();
        ClassLoader classLoader = getClass().getClassLoader();
        Gson               gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(classLoader.getResource(offerCalendar1).getFile()));
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, CalendarPrice>>(){}.getType());

        calendarStatusService = new CalendarStatusServiceImpl(10000l,15000l);
        calendarStatusService.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));

        calendarStatusService.addPrices(calendarPrice.getSpecialPrice().getPrice(), convertDate(calendarPrice.getSpecialPrice().getDays())); // Устанавливаем специальную цену на отдельные дни
        return new ResponseEntity<>(calendarStatusService.toJson(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCalendar(@PathVariable String offerId){
//                                                 @PathVariable String day){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        JsonParser       parser = new JsonParser();
        ClassLoader classLoader = getClass().getClassLoader();
        Gson               gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(classLoader.getResource(offerCalendar1).getFile()));
            jsonRents = (JsonObject) parser.parse(new FileReader(classLoader.getResource(offerRents).getFile()));
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, CalendarPrice>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());

        calendarStatusService = new CalendarStatusServiceImpl(10000l,15000l);
        calendarStatusService.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));

//        if (day!=null)
//            return new ResponseEntity<>(calendarStatusService.delPrices(convertDate(day)).toString(), HttpStatus.OK);
//        else
            return new ResponseEntity<>(calendarStatusService.delPrices(convertDate(rents.get("delete42").getDays())).toString(), HttpStatus.OK);
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
