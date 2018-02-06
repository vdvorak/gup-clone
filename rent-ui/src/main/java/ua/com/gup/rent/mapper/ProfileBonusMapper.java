package ua.com.gup.rent.mapper;

import com.mifmif.common.regex.Generex;
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

        if (profileBonus != null) {

            profileBonusDTO.setId(profileBonus.getId());
            profileBonusDTO.setCode(profileBonus.getCode());
            profileBonusDTO.setName(profileBonus.getName());

            profileBonusDTO.setScenarios(profileBonus.getScenarios());
            profileBonusDTO.setManagerPublicId(profileBonus.getManagerPublicId());
            profileBonusDTO.setBonusAmount(profileBonus.getBonusAmount());

            profileBonusDTO.setActive(profileBonus.getActive());
            profileBonusDTO.setCountUse(profileBonus.getCountUse());

            profileBonusDTO.setCreateDate(profileBonus.getCreateDate());
            profileBonusDTO.setStartDate(profileBonus.getStartDate());
            profileBonusDTO.setEndDate(profileBonus.getEndDate());
        }

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
            addItem.setManagerPublicId(item.getManagerPublicId());
            addItem.setBonusAmount(item.getBonusAmount());

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
        profileBonus.setScenarios(profileBonusDTO.getScenarios());
        if (isRentOfferEditBonusDTO && ((ProfileEditBonusDTO) profileBonusDTO).getId() != null) {
            //update||edit
            ProfileEditBonusDTO item = (ProfileEditBonusDTO) profileBonusDTO;
            profileBonus.setId(item.getId());
            profileBonus.setCode(item.getCode());
            profileBonus.setName(item.getName());

            //check scenario
            profileBonus.setManagerPublicId(item.getManagerPublicId());
            profileBonus.setBonusAmount(item.getBonusAmount());
        } else {
            //if only create not edit or update
            if (profileBonusDTO.getCreateDate() == null) {
                //create||generation code
                String expReg="\\w{5}-\\w{5}";
                Generex code = new Generex(expReg);
                ProfileCreateBonusDTO item = (ProfileCreateBonusDTO) profileBonusDTO;
                profileBonus.setCode(code.random());
                profileBonus.setName(item.getName());
                profileBonus.setCreateDate(LocalDateTime.now());

                //check scenario
                profileBonus.setManagerPublicId(item.getManagerPublicId());
                profileBonus.setBonusAmount(item.getBonusAmount());
            }
        }





        profileBonus.setActive(profileBonusDTO.getActive());
        profileBonus.setCountUse(profileBonusDTO.getCountUse());

        profileBonus.setStartDate(profileBonusDTO.getStartDate());
        profileBonus.setEndDate(profileBonusDTO.getEndDate());

        return profileBonus;
    }
}
