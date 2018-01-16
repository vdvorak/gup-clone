package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.common.model.mongo.offer.Lands;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;

@Component
public class RentOfferLandsMapper {

    public RentOfferLandsDTO fromModelToDTO(Lands rentOfferLands) {
        RentOfferLandsDTO rentOfferLandsDTO = null;
        if (rentOfferLands != null) {
            rentOfferLandsDTO = new RentOfferLandsDTO(rentOfferLands.getCadnums(), rentOfferLands.getPolygons());
        }
        return rentOfferLandsDTO;
    }

    public Lands fromDTOToModel(RentOfferLandsDTO rentOfferLandsDTO) {
        Lands rentOfferLands = null;
        if (rentOfferLandsDTO != null) {
            rentOfferLands = new Lands(rentOfferLandsDTO.getCadnums(), rentOfferLandsDTO.getPolygons());
        }
        return rentOfferLands;
    }
}