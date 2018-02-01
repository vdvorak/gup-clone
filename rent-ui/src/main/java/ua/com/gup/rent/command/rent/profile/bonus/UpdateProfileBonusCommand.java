package ua.com.gup.rent.command.rent.profile.bonus;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.mapper.ProfileBonusMapper;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;

public class UpdateProfileBonusCommand extends ProfileBonusCommand {
    private ProfileEditBonusDTO profileEditBonusDTO;

    public UpdateProfileBonusCommand(ProfileBonusService profileBonusService, ProfileEditBonusDTO profileEditBonusDTO, ProfileBonusMapper profileBonusMapper) {
        super(profileBonusService, "", profileBonusMapper);
        this.profileEditBonusDTO = profileEditBonusDTO;
    }

    @Override
    public ProfileBonus execute() throws Exception {

           profileBonusService.update(profileEditBonusDTO);
           profileBonus = this.profileBonusMapper.fromDTOToModel(profileBonusService.findOneByCode(profileEditBonusDTO.getCode()));
           profileBonusId = profileBonus.getId();

        return profileBonus;

    }


    public String getObjectId() {
        return profileBonusId;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.PROFILE_BONUS_UPDATE;
    }
}
