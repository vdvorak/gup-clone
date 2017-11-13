package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.dto.PriceDTO;
import ua.com.gup.rent.model.Price;

@Component
public class RentPriceMapper {

    public PriceDTO fromModelToDTO(Price p) {
        PriceDTO priceDTO = new PriceDTO();
        if (p != null) {
            priceDTO.setBusinessDayCost(p.getBusinessDayCost());
            priceDTO.setWeekendDayCost(p.getWeekendDayCost());
            priceDTO.setHolidayDayCost(p.getHolidayDayCost());
        }
        return priceDTO;
    }

    public Price fromDTOToModel(PriceDTO priceDto) {
        Price price = null;
        if (priceDto != null) {
            price = new Price();
            price.setBusinessDayCost(priceDto.getBusinessDayCost());
            price.setWeekendDayCost(priceDto.getWeekendDayCost());
            price.setHolidayDayCost(priceDto.getHolidayDayCost());
        }
        return price;
    }
}
