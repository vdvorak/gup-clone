package ua.com.gup.rent.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(path = "/api/calendars")
public class RentCalendarEndpoint {

    private static final Map<Integer, ua.com.gup.rent.model.rent.RentCalendarYear> calendarsMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void initialize() {
        LocalDate now = LocalDate.now();
        calendarsMap.put(now.getYear(), ua.com.gup.rent.util.RentCalendarUtil.generateForYear(now));
    }


    @RequestMapping(path = "/default", method = RequestMethod.GET)
    public ResponseEntity getDefaultCalendar() {
        LocalDate now = LocalDate.now();
        ua.com.gup.rent.model.rent.RentCalendarYear rentCalendarYear = null;
        if (calendarsMap.containsKey(now.getYear())) {
            rentCalendarYear = calendarsMap.get(now.getYear());
            return new ResponseEntity(rentCalendarYear, HttpStatus.OK);
        }

        rentCalendarYear = ua.com.gup.rent.util.RentCalendarUtil.generateForYear(now);
        calendarsMap.put(now.getYear(), ua.com.gup.rent.util.RentCalendarUtil.generateForYear(now));

        return new ResponseEntity(rentCalendarYear, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/year/{year}", method = RequestMethod.GET)
    public ResponseEntity getCalendarForYear(@PathVariable(name = "year") Integer year) {
        LocalDate now = LocalDate.of(year, Month.JANUARY, 1);
        ua.com.gup.rent.model.rent.RentCalendarYear rentCalendarYear = null;
        if (calendarsMap.containsKey(now.getYear())) {
            rentCalendarYear = calendarsMap.get(now.getYear());
            return new ResponseEntity(rentCalendarYear, HttpStatus.OK);
        }

        rentCalendarYear = ua.com.gup.rent.util.RentCalendarUtil.generateForYear(now);
        calendarsMap.put(now.getYear(), ua.com.gup.rent.util.RentCalendarUtil.generateForYear(now));

        return new ResponseEntity(rentCalendarYear, HttpStatus.CREATED);
    }

}
