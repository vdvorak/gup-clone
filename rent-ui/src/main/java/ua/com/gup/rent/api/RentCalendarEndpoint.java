package ua.com.gup.rent.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.rent.model.CalendarYear;
import ua.com.gup.rent.util.CalendarUtil;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(path = "/api/calendars")
public class RentCalendarEndpoint {

    private static final Map<Integer, CalendarYear> calendarsMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void initialize() {
        LocalDate now = LocalDate.now();
        calendarsMap.put(now.getYear(), CalendarUtil.generateForYear(now));
    }


    @RequestMapping(path = "/default", method = RequestMethod.GET)
    public ResponseEntity getDefaultCalendar() {
        LocalDate now = LocalDate.now();
        CalendarYear calendarYear = null;
        if (calendarsMap.containsKey(now.getYear())) {
            calendarYear = calendarsMap.get(now.getYear());
            return new ResponseEntity(calendarYear, HttpStatus.OK);
        }

        calendarYear = CalendarUtil.generateForYear(now);
        calendarsMap.put(now.getYear(), CalendarUtil.generateForYear(now));

        return new ResponseEntity(calendarYear, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/year/{year}", method = RequestMethod.GET)
    public ResponseEntity getCalendarForYear(@PathVariable(name = "year") Integer year) {
        LocalDate now = LocalDate.of(year, Month.JANUARY, 1);
        CalendarYear calendarYear = null;
        if (calendarsMap.containsKey(now.getYear())) {
            calendarYear = calendarsMap.get(now.getYear());
            return new ResponseEntity(calendarYear, HttpStatus.OK);
        }

        calendarYear = CalendarUtil.generateForYear(now);
        calendarsMap.put(now.getYear(), CalendarUtil.generateForYear(now));

        return new ResponseEntity(calendarYear, HttpStatus.CREATED);
    }

}
