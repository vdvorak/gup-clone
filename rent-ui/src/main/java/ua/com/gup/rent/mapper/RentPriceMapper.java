package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;

@Component
public class RentPriceMapper {

    public ua.com.gup.rent.dto.RentPriceDTO fromModelToDTO(ua.com.gup.rent.model.rent.RentPrice p) {
        ua.com.gup.rent.dto.RentPriceDTO rentPriceDTO = new ua.com.gup.rent.dto.RentPriceDTO();
        if (p != null) {
            rentPriceDTO.setBusinessDayCost(p.getBusinessDayCost());
            rentPriceDTO.setWeekendDayCost(p.getWeekendDayCost());
            rentPriceDTO.setHolidayDayCost(p.getHolidayDayCost());
        }
        return rentPriceDTO;
    }

    public ua.com.gup.rent.model.rent.RentPrice fromDTOToModel(ua.com.gup.rent.dto.RentPriceDTO rentPriceDto) {
        ua.com.gup.rent.model.rent.RentPrice rentPrice = null;
        if (rentPriceDto != null) {
            rentPrice = new ua.com.gup.rent.model.rent.RentPrice();
            rentPrice.setBusinessDayCost(rentPriceDto.getBusinessDayCost());
            rentPrice.setWeekendDayCost(rentPriceDto.getWeekendDayCost());
            rentPrice.setHolidayDayCost(rentPriceDto.getHolidayDayCost());
        }
        return rentPrice;
    }
}
