package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.RentPrice;
import ua.com.gup.rent.service.dto.price.RentPriceDTO;

@Component
public class RentPriceMapper {

    public RentPriceDTO fromModelToDTO(RentPrice p) {
        RentPriceDTO rentObjectPriceDTO = new RentPriceDTO();
        if (p != null) {
            rentObjectPriceDTO.setBusinessDayCost(p.getBusinessDayCost());
            rentObjectPriceDTO.setWeekendDayCost(p.getWeekendDayCost());
            rentObjectPriceDTO.setHolidayDayCost(p.getHolidayDayCost());
        }
        return rentObjectPriceDTO;
    }

    public RentPrice fromDTOToModel(RentPriceDTO rentObjectPriceDto) {
        RentPrice price = null;
        if (rentObjectPriceDto != null) {
            price = new RentPrice();
            price.setBusinessDayCost(rentObjectPriceDto.getBusinessDayCost());
            price.setWeekendDayCost(rentObjectPriceDto.getWeekendDayCost());
            price.setHolidayDayCost(rentObjectPriceDto.getHolidayDayCost());
        }
        return price;
    }
}
