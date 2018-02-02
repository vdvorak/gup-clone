package ua.com.gup.rent.command.rent.profile.bonus;

import lombok.Data;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;

@Data
public class UpdateProfileBonusCommand extends ProfileBonusCommand {
    private OperationType operationType;

    public UpdateProfileBonusCommand(ProfileBonusService profileBonusService, ProfileBonus profileBonus, OperationType operationType) {
        super(profileBonusService, profileBonus);
        this.operationType = operationType;
    }

    @Override
    public ProfileBonus execute() throws Exception {

        profileBonusService.update(profileBonus);
        profileBonusId = profileBonus.getId();

        return profileBonus;

    }


    public String getObjectId() {
        return profileBonusId;
    }

    @Override
    public OperationType getOperationType() {
        OperationType operationType = null;


        switch (operationType) {

            case PROFILE_BONUS_UPDATE:
                operationType = OperationType.PROFILE_BONUS_UPDATE;
                break;

        }

        return operationType;
    }

}
