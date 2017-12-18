package ua.com.gup.server.command;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

public class UnbanUserCommand extends UserCommand {

    public UnbanUserCommand(Profile profile, ProfilesService profilesService) {
        super(profile, profilesService);
    }

    @Override
    public void execute() throws Exception {
        profilesService.unbanProfile(profile);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.USER_UNBAN;
    }
}
