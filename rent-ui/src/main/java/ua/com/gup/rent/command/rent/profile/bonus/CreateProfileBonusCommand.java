package ua.com.gup.rent.command.rent.profile.bonus;

import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.mapper.ProfileBonusMapper;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;

public class CreateProfileBonusCommand extends ProfileBonusCommand {
    private CommonProfileBonusDTO profileCreateBonusDTO;

    public CreateProfileBonusCommand(ProfileBonusService profileBonusService, CommonProfileBonusDTO profileCreateBonusDTO, ProfileBonusMapper profileBonusMapper) {
        super(profileBonusService, "", profileBonusMapper);
        this.profileCreateBonusDTO = profileCreateBonusDTO;
    }

    @Override
    public ProfileBonus execute() throws Exception {

           profileBonusService.save(profileCreateBonusDTO);
           profileBonus = this.profileBonusMapper.fromDTOToModel(profileBonusService.findOneByCode(((ProfileCreateBonusDTO)profileCreateBonusDTO).getCode()));
           profileBonusId = profileBonus.getId();

        return profileBonus;

    }


    public String getObjectId() {
        return profileBonusId;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.PROFILE_BONUS_CREATED;
    }
}
