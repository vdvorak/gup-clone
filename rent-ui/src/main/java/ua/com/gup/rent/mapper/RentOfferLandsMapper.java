package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.RentOfferLands;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;

@Component
public class RentOfferLandsMapper {

    public RentOfferLandsDTO fromModelToDTO(RentOfferLands rentOfferLands) {
        RentOfferLandsDTO rentOfferLandsDTO = null;
        if (rentOfferLands != null) {
            rentOfferLandsDTO = new RentOfferLandsDTO(rentOfferLands.getCadnums(), rentOfferLands.getPolygons());
        }
        return rentOfferLandsDTO;
    }

    public RentOfferLands fromDTOToModel(RentOfferLandsDTO rentOfferLandsDTO) {
        RentOfferLands rentOfferLands = null;
        if (rentOfferLandsDTO != null) {
            rentOfferLands = new RentOfferLands(rentOfferLandsDTO.getCadnums(), rentOfferLandsDTO.getPolygons());
        }
        return rentOfferLands;
    }
}