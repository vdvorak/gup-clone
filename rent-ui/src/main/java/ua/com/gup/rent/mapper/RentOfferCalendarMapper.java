package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.service.dto.rent.offer.calendar.RentOfferCalendarDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class RentOfferCalendarMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public RentOfferCalendarDTO fromModelToDTO(RentOfferCalendar rentOfferCalendar) {
        RentOfferCalendarDTO rentOfferCalendarDTO = new RentOfferCalendarDTO();
        rentOfferCalendarDTO.setStartDate(LocalDate.parse(rentOfferCalendar.getRentStartDate(), formatter));
        rentOfferCalendarDTO.setEndDate(LocalDate.parse(rentOfferCalendar.getRentEndDate(), formatter));
        rentOfferCalendarDTO.setDays(Arrays.asList(rentOfferCalendar.getDays()));
        return rentOfferCalendarDTO;
    }
}