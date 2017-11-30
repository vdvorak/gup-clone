package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.Price;

@Component
public class RentPriceMapper {

    public ua.com.gup.rent.dto.RentPriceDTO fromModelToDTO(Price p) {
        ua.com.gup.rent.dto.RentPriceDTO rentPriceDTO = new ua.com.gup.rent.dto.RentPriceDTO();
        if (p != null) {
            rentPriceDTO.setBusinessDayCost(p.getBusinessDayCost());
            rentPriceDTO.setWeekendDayCost(p.getWeekendDayCost());
            rentPriceDTO.setHolidayDayCost(p.getHolidayDayCost());
        }
        return rentPriceDTO;
    }

    public Price fromDTOToModel(ua.com.gup.rent.dto.RentPriceDTO rentPriceDto) {
        Price price = null;
        if (rentPriceDto != null) {
            price = new Price();
            price.setBusinessDayCost(rentPriceDto.getBusinessDayCost());
            price.setWeekendDayCost(rentPriceDto.getWeekendDayCost());
            price.setHolidayDayCost(rentPriceDto.getHolidayDayCost());
        }
        return price;
    }
}
