package ua.com.gup.rent.command.rent.profile.bonus;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.command.rent.profile.bonus.scenario.IBonusScenario;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;

public class ExecuteScenarioProfileBonusCommand extends ProfileBonusCommand {

    private IBonusScenario scenario;
    //private

    public ExecuteScenarioProfileBonusCommand(ProfileBonusService profileBonusService, ProfileBonus profileBonus) {
        super(profileBonusService, profileBonus);
    }

    @Override
    public ProfileBonus execute() throws Exception {
        switch (this.profileBonus.getScenarios()){
            case REFERENCE_MANAGER : scenario = new IBonusScenario() {
                @Override
                public void executeScenario() {
                    //todo scenario if REFERENCE_MANAGER add managerId in user's

                }
            };
                 break;
            case BONUS_MONEY : scenario = new IBonusScenario() {
                @Override
                public void executeScenario() {
                    //todo scenario if BONUS_MONEY  add bonus_amount  in user's

                }
            } ;
                break;
        }

        return profileBonus;
    }

    public String getObjectId() {
        return profileBonusId;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.PROFILE_BONUS_EXECUTE_SCENARIO;
    }
}
