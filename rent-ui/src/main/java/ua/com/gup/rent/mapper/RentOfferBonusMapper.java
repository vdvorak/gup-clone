package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.service.dto.bonus.RentOfferCreateBonusDTO;

import java.time.LocalDateTime;

/**
 * @author Victor Dvorak
 **/
@Component
public class RentOfferBonusMapper {
    public RentOfferCreateBonusDTO fromModelToDTO(RentOfferBonus rentOfferBonus) {
        RentOfferCreateBonusDTO rentOfferCreateBonusDTO = new RentOfferCreateBonusDTO();

        rentOfferCreateBonusDTO.setCode(rentOfferBonus.getCode());
        rentOfferCreateBonusDTO.setName(rentOfferBonus.getName());
        rentOfferCreateBonusDTO.setScenarios(rentOfferBonus.getScenarios());
        rentOfferCreateBonusDTO.setActive(rentOfferBonus.getActive());
        rentOfferCreateBonusDTO.setCountUse(rentOfferBonus.getCountUse());

        rentOfferCreateBonusDTO.setCreateDate(rentOfferBonus.getCreateDate());
        rentOfferCreateBonusDTO.setStartDate(rentOfferBonus.getStartDate());
        rentOfferCreateBonusDTO.setEndDate(rentOfferBonus.getEndDate());

        return rentOfferCreateBonusDTO;
    }

    public RentOfferBonus fromDTOToModel(RentOfferCreateBonusDTO rentOfferCreateBonusDTO) {

        RentOfferBonus rentOfferBonus = new RentOfferBonus();

        //todo generation form server
        rentOfferBonus.setCode(rentOfferCreateBonusDTO.getCode());
        rentOfferBonus.setName(rentOfferCreateBonusDTO.getName());
        rentOfferBonus.setScenarios(rentOfferCreateBonusDTO.getScenarios());
        rentOfferBonus.setActive(rentOfferCreateBonusDTO.getActive());
        rentOfferBonus.setCountUse(rentOfferCreateBonusDTO.getCountUse());
        //if only create not edit or update
        if (rentOfferBonus.getCreateDate() != null) {
            rentOfferBonus.setCreateDate(LocalDateTime.now());
        }
        rentOfferBonus.setStartDate(rentOfferCreateBonusDTO.getStartDate());
        rentOfferBonus.setEndDate(rentOfferCreateBonusDTO.getEndDate());

        return rentOfferBonus;
    }
}
