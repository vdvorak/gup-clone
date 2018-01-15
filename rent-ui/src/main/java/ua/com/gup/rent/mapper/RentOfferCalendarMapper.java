package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.service.dto.rent.offer.calendar.RentOfferCalendarDTO;

@Component
public class RentOfferCalendarMapper {

    public RentOfferCalendarDTO fromModelToDTO(RentOfferCalendar rentOfferCalendar) {
        return null;
    }

    public RentOfferCalendar fromDTOToModel(RentOfferCalendarDTO rentOfferCalendarDTO) {
        RentOfferCalendar rentOfferCalendar = new RentOfferCalendar();
        rentOfferCalendar.setDays(rentOfferCalendarDTO.getDays());
        rentOfferCalendar.setRentStartDate(rentOfferCalendarDTO.getStartDate());
        rentOfferCalendar.setRentEndDate(rentOfferCalendarDTO.getEndDate());
        return rentOfferCalendar;
    }
}