package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.profile.bonus.RentOfferProfileBonus;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;

import java.time.ZonedDateTime;

/**
 * @author Victor Dvorak
 **/
@Component
public class RentOfferProfileBonusMapper {
    public ProfileCreateBonusDTO fromModelToDTO(RentOfferProfileBonus rentOfferProfileBonus) {
        ProfileCreateBonusDTO profileCreateBonusDTO = new ProfileCreateBonusDTO();

        profileCreateBonusDTO.setCode(rentOfferProfileBonus.getCode());
        profileCreateBonusDTO.setName(rentOfferProfileBonus.getName());
        profileCreateBonusDTO.setScenarios(rentOfferProfileBonus.getScenarios());
        profileCreateBonusDTO.setActive(rentOfferProfileBonus.getActive());
        profileCreateBonusDTO.setCountUse(rentOfferProfileBonus.getCountUse());

        profileCreateBonusDTO.setCreateDate(rentOfferProfileBonus.getCreateDate());
        profileCreateBonusDTO.setStartDate(rentOfferProfileBonus.getStartDate());
        profileCreateBonusDTO.setEndDate(rentOfferProfileBonus.getEndDate());

        return profileCreateBonusDTO;
    }

    public RentOfferProfileBonus fromDTOToModel(ProfileCreateBonusDTO profileCreateBonusDTO) {

        RentOfferProfileBonus rentOfferProfileBonus = new RentOfferProfileBonus();

        //todo generation form server
        rentOfferProfileBonus.setCode(profileCreateBonusDTO.getCode());
        rentOfferProfileBonus.setName(profileCreateBonusDTO.getName());
        rentOfferProfileBonus.setScenarios(profileCreateBonusDTO.getScenarios());
        rentOfferProfileBonus.setActive(profileCreateBonusDTO.getActive());
        rentOfferProfileBonus.setCountUse(profileCreateBonusDTO.getCountUse());
        //if only create not edit or update
        if (rentOfferProfileBonus.getCreateDate() != null) {
            rentOfferProfileBonus.setCreateDate(ZonedDateTime.now());
        }
        rentOfferProfileBonus.setStartDate(profileCreateBonusDTO.getStartDate());
        rentOfferProfileBonus.setEndDate(profileCreateBonusDTO.getEndDate());

        return rentOfferProfileBonus;
    }
}
