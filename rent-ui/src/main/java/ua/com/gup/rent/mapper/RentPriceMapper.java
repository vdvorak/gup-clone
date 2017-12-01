package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.dto.rentobject.price.RentObjectPriceDTO;
import ua.com.gup.rent.model.Price;

@Component
public class RentPriceMapper {

    public RentObjectPriceDTO fromModelToDTO(Price p) {
        RentObjectPriceDTO rentObjectPriceDTO = new RentObjectPriceDTO();
        if (p != null) {
            rentObjectPriceDTO.setBusinessDayCost(p.getBusinessDayCost());
            rentObjectPriceDTO.setWeekendDayCost(p.getWeekendDayCost());
            rentObjectPriceDTO.setHolidayDayCost(p.getHolidayDayCost());
        }
        return rentObjectPriceDTO;
    }

    public Price fromDTOToModel(RentObjectPriceDTO rentObjectPriceDto) {
        Price price = null;
        if (rentObjectPriceDto != null) {
            price = new Price();
            price.setBusinessDayCost(rentObjectPriceDto.getBusinessDayCost());
            price.setWeekendDayCost(rentObjectPriceDto.getWeekendDayCost());
            price.setHolidayDayCost(rentObjectPriceDto.getHolidayDayCost());
        }
        return price;
    }
}
