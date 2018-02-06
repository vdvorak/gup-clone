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
        //convert DTO to  Domain Model
        ProfileBonus profileBonus = new ProfileBonus();
        //check instance object type{class} update
        boolean IsProfileEditBonusDTO = ProfileEditBonusDTO.class.isInstance(profileBonusDTO);
        //check instance object type{class} create
        boolean isProfileCreateBonusDTO = ProfileCreateBonusDTO.class.isInstance(profileBonusDTO);

        //if not exist object doing create object
        if (isProfileCreateBonusDTO) {
            //generation bonus code
            String bonusCode = new Generex("([a-zA-Z0-9]{5}\\-[a-zA-Z0-9]{5})").random();

            ProfileCreateBonusDTO profileCreateBonusDTO = (ProfileCreateBonusDTO) profileBonusDTO;

            profileBonus.setName(profileCreateBonusDTO.getName());
            profileBonus.setCode(bonusCode);

            //from server backend
            profileBonus.setCreateDate(LocalDateTime.now());

            profileBonus.setActive(profileCreateBonusDTO.getActive());
            profileBonus.setCountUse(profileCreateBonusDTO.getCountUse());
            profileBonus.setStartDate(profileCreateBonusDTO.getStartDate());
            profileBonus.setEndDate(profileCreateBonusDTO.getEndDate());

            //check scenario form bonus
            profileBonus.setScenarios(profileCreateBonusDTO.getScenarios());
            // 1 - scenario   managerPublicId
            profileBonus.setManagerPublicId(profileCreateBonusDTO.getManagerPublicId());
            //2  , scenario bonusAmount
            profileBonus.setBonusAmount(profileCreateBonusDTO.getBonusAmount());
        }

        //if exists object doing update field's
        if (IsProfileEditBonusDTO) {
            ProfileEditBonusDTO profileEditBonusDTO = (ProfileEditBonusDTO) profileBonusDTO;
            profileBonus.setId(profileEditBonusDTO.getId());
            profileBonus.setCode(profileEditBonusDTO.getCode());
            profileBonus.setName(profileEditBonusDTO.getName());
            profileBonus.setActive(profileEditBonusDTO.getActive());
            profileBonus.setCountUse(profileEditBonusDTO.getCountUse());
            profileBonus.setStartDate(profileEditBonusDTO.getStartDate());
            profileBonus.setEndDate(profileEditBonusDTO.getEndDate());
        }


        return profileBonus;
    }
}
