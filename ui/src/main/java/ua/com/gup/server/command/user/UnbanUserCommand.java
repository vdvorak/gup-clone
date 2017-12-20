package ua.com.gup.server.command.user;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

public class UnbanUserCommand extends UserCommand {

    public UnbanUserCommand(Profile profile, ProfilesService profilesService) {
        super(profile, profilesService);
    }

    @Override
    public Profile execute() throws Exception {
        profilesService.unbanProfile(profile);
        return profile;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.USER_UNBAN;
    }
}
