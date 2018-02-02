package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Dvorak
 **/
@Component
public class ProfileBonusMapper {
    public CommonProfileBonusDTO fromModelToDTO(ProfileBonus profileBonus) {

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

    public List<CommonProfileBonusDTO> fromModelToDTO(List<ProfileBonus> profileBonusList) {

        List<CommonProfileBonusDTO> profileBonusListDTO = new ArrayList<CommonProfileBonusDTO>();

        for (ProfileBonus item : profileBonusList) {

            ProfileEditBonusDTO addItem = new ProfileEditBonusDTO();

            addItem.setId(item.getId());
            addItem.setCode(item.getCode());
            addItem.setName(item.getName());
            addItem.setScenarios(item.getScenarios());
            addItem.setActive(item.getActive());
            addItem.setCountUse(item.getCountUse());
            addItem.setCreateDate(item.getCreateDate());
            addItem.setStartDate(item.getStartDate());
            addItem.setEndDate(item.getEndDate());

            profileBonusListDTO.add(addItem);
        }


        return profileBonusListDTO;
    }

    public ProfileBonus fromDTOToModel(CommonProfileBonusDTO profileBonusDTO) {

        boolean isRentOfferEditBonusDTO = ProfileEditBonusDTO.class.isInstance(profileBonusDTO);
        ProfileBonus profileBonus = new ProfileBonus();

        if (isRentOfferEditBonusDTO && ((ProfileEditBonusDTO) profileBonusDTO).getId() != null) {
            //if update
            ProfileEditBonusDTO item = (ProfileEditBonusDTO) profileBonusDTO;
            profileBonus.setId(item.getId());
            profileBonus.setCode(item.getCode());
            profileBonus.setName(item.getName());
        } else {
            //if create
            ProfileCreateBonusDTO item = (ProfileCreateBonusDTO) profileBonusDTO;
            //todo generation form server
            profileBonus.setCode(item.getCode());
            profileBonus.setName(item.getName());
        }

        profileBonus.setScenarios(profileBonusDTO.getScenarios());
        profileBonus.setActive(profileBonusDTO.getActive());
        profileBonus.setCountUse(profileBonusDTO.getCountUse());
        //if only create not edit or update
        if (profileBonusDTO.getCreateDate() != null) {
            profileBonus.setCreateDate(LocalDateTime.now());
        }
        profileBonus.setStartDate(profileBonusDTO.getStartDate());
        profileBonus.setEndDate(profileBonusDTO.getEndDate());

        return profileBonus;
    }
}
