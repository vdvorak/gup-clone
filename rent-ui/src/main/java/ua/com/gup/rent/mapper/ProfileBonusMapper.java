package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;

import java.time.ZonedDateTime;

/**
 * @author Victor Dvorak
 **/
@Component
public class ProfileBonusMapper {
    public ProfileEditBonusDTO fromModelToDTO(ProfileBonus profileBonus) {
        ProfileEditBonusDTO profileBonusDTO = new ProfileEditBonusDTO();

        profileBonusDTO.setId(profileBonus.getId());
        profileBonusDTO.setCode(profileBonus.getCode());
        profileBonusDTO.setName(profileBonus.getName());
        profileBonusDTO.setScenarios(profileBonus.getScenarios());
        profileBonusDTO.setActive(profileBonus.getActive());
        profileBonusDTO.setCountUse(profileBonus.getCountUse());

        profileBonusDTO.setCreateDate(profileBonus.getCreateDate());
        profileBonusDTO.setStartDate(profileBonus.getStartDate());
        profileBonusDTO.setEndDate(profileBonus.getEndDate());

        return profileBonusDTO;
    }

    public ProfileBonus fromDTOToModel(ProfileEditBonusDTO profileBonusDTO) {

        ProfileBonus profileBonus = new ProfileBonus();

        if(profileBonusDTO.getId()!=null){
            profileBonus.setId(profileBonusDTO.getId());
        }
        //todo generation form server
        profileBonus.setCode(profileBonusDTO.getCode());
        profileBonus.setName(profileBonusDTO.getName());
        profileBonus.setScenarios(profileBonusDTO.getScenarios());
        profileBonus.setActive(profileBonusDTO.getActive());
        profileBonus.setCountUse(profileBonusDTO.getCountUse());
        //if only create not edit or update
        if (profileBonusDTO.getCreateDate() != null) {
            profileBonus.setCreateDate(ZonedDateTime.now());
        }
        profileBonus.setStartDate(profileBonusDTO.getStartDate());
        profileBonus.setEndDate(profileBonusDTO.getEndDate());

        return profileBonus;
    }
}
