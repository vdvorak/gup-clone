package ua.com.gup.rent.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarYear;
import ua.com.gup.rent.util.RentCalendarUtil;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(path = "/api/calendars")
public class RentOfferCalendarEndpoint {

    private static final Map<Integer, RentOfferCalendarYear> calendarsMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void initialize() {
        LocalDate now = LocalDate.now();
        calendarsMap.put(now.getYear(),RentCalendarUtil.generateForYear(now));
    }


    @RequestMapping(path = "/default", method = RequestMethod.GET)
    public ResponseEntity getDefaultCalendar() {
        LocalDate now = LocalDate.now();
        RentOfferCalendarYear rentOfferCalendarYear = null;
        if (calendarsMap.containsKey(now.getYear())) {
            rentOfferCalendarYear = calendarsMap.get(now.getYear());
            return new ResponseEntity(rentOfferCalendarYear, HttpStatus.OK);
        }

        rentOfferCalendarYear = RentCalendarUtil.generateForYear(now);
        calendarsMap.put(now.getYear(), RentCalendarUtil.generateForYear(now));

        return new ResponseEntity(rentOfferCalendarYear, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/year/{year}", method = RequestMethod.GET)
    public ResponseEntity getCalendarForYear(@PathVariable(name = "year") Integer year) {
        LocalDate now = LocalDate.of(year, Month.JANUARY, 1);
        RentOfferCalendarYear rentOfferCalendarYear = null;
        if (calendarsMap.containsKey(now.getYear())) {
            rentOfferCalendarYear = calendarsMap.get(now.getYear());
            return new ResponseEntity(rentOfferCalendarYear, HttpStatus.OK);
        }

        rentOfferCalendarYear = RentCalendarUtil.generateForYear(now);
        calendarsMap.put(now.getYear(), RentCalendarUtil.generateForYear(now));

        return new ResponseEntity(rentOfferCalendarYear, HttpStatus.CREATED);
    }

}
