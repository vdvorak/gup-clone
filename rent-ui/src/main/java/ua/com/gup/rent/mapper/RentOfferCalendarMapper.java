package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.service.dto.rent.offer.calendar.RentOfferCalendarDTO;

@Component
public class RentOfferCalendarMapper {

    public RentOfferCalendarDTO fromModelToDTO(RentOfferCalendar rentOfferCalendar) {
        RentOfferCalendarDTO rentOfferCalendarDTO = new RentOfferCalendarDTO();
        rentOfferCalendarDTO.setDays(rentOfferCalendar.getDays());
        rentOfferCalendarDTO.setYear(rentOfferCalendar.getYear());
        return rentOfferCalendarDTO;
    }

    public RentOfferCalendar fromDTOToModel(RentOfferCalendarDTO rentOfferCalendarDTO) {
        RentOfferCalendar rentOfferCalendar = new RentOfferCalendar();
        rentOfferCalendar.setDays(rentOfferCalendarDTO.getDays());
        rentOfferCalendar.setYear(rentOfferCalendarDTO.getYear());

        return rentOfferCalendar;
    }
}