package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.service.dto.bonus.RentOfferBonusDTO;

/**
 * @author Victor Dvorak
 **/
@Component
public class RentOfferBonusMapper {
    public RentOfferBonusDTO fromModelToDTO(RentOfferBonus rentOfferBonus) {
        RentOfferBonusDTO rentOfferBonusDTO = new RentOfferBonusDTO();
        return rentOfferBonusDTO;
    }

    public RentOfferBonus fromDTOToModel(RentOfferBonusDTO rentOfferBonusDTO) {
        RentOfferBonus rentOfferBonus = new RentOfferBonus();
        return rentOfferBonus;
    }
}
