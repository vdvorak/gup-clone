package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.dto.rentobject.price.RentObjectPriceDTO;
import ua.com.gup.rent.model.rent.RentPrice;

@Component
public class RentPriceMapper {

    public RentObjectPriceDTO fromModelToDTO(RentPrice p) {
        RentObjectPriceDTO rentObjectPriceDTO = new RentObjectPriceDTO();
        if (p != null) {
            rentObjectPriceDTO.setBusinessDayCost(p.getBusinessDayCost());
            rentObjectPriceDTO.setWeekendDayCost(p.getWeekendDayCost());
            rentObjectPriceDTO.setHolidayDayCost(p.getHolidayDayCost());
        }
        return rentObjectPriceDTO;
    }

    public RentPrice fromDTOToModel(RentObjectPriceDTO rentObjectPriceDto) {
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
