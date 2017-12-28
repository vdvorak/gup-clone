package ua.com.gup.rent.api;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.util.RentCalendarUtil;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/api/calendars")
public class RentOfferCalendarEndpoint {


    @RequestMapping(path = "/default", method = RequestMethod.GET)
    public ResponseEntity getDefaultCalendar(@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        LocalDate now = LocalDate.now();
        RentOfferCalendar rentOfferCalendar = endDate == null ? RentCalendarUtil.generateCalendarForDates(now) : RentCalendarUtil.generateCalendarForDates(now, endDate);
        return new ResponseEntity(rentOfferCalendar, HttpStatus.CREATED);
    }

}
