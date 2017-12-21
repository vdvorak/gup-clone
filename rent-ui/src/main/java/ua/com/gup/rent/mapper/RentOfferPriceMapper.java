package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.price.RentOfferPrice;
import ua.com.gup.rent.service.dto.rent.offer.price.RentOfferPriceDTO;

@Component
public class RentOfferPriceMapper {

    public RentOfferPriceDTO fromModelToDTO(RentOfferPrice rentOfferPrice) {
        RentOfferPriceDTO rentOfferPriceDTO = new RentOfferPriceDTO();
        rentOfferPriceDTO.setBusinessDayCost(rentOfferPrice.getBusinessDayCost());
        rentOfferPriceDTO.setWeekendDayCost(rentOfferPrice.getWeekendDayCost());
        rentOfferPriceDTO.setHolidayDayCost(rentOfferPrice.getHolidayDayCost());
        rentOfferPriceDTO.setPrepaymentPercent(rentOfferPrice.getPrepaymentPercent());
        return rentOfferPriceDTO;
    }

    public RentOfferPrice fromDTOToModel(RentOfferPriceDTO rentOfferPriceDTO) {
        RentOfferPrice rentOfferPrice = new RentOfferPrice();
        rentOfferPrice.setBusinessDayCost(rentOfferPriceDTO.getBusinessDayCost());
        rentOfferPrice.setWeekendDayCost(rentOfferPriceDTO.getWeekendDayCost());
        rentOfferPrice.setHolidayDayCost(rentOfferPriceDTO.getHolidayDayCost());
        rentOfferPrice.setPrepaymentPercent(rentOfferPriceDTO.getPrepaymentPercent());
        return rentOfferPrice;
    }
}