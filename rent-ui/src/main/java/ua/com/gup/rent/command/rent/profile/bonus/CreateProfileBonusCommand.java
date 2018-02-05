package ua.com.gup.rent.command.rent.profile.bonus;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;

public class CreateProfileBonusCommand extends ProfileBonusCommand {


    public CreateProfileBonusCommand(ProfileBonusService profileBonusService, ProfileBonus profileBonus) {
        super(profileBonusService, profileBonus);
    }

    @Override
    public ProfileBonus execute() throws Exception {

        profileBonusService.save(profileBonus);
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
