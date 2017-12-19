package ua.com.gup.server.command.user;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

public class EditUserCommand extends UserCommand {


    public EditUserCommand(Profile profile, ProfilesService profilesService) {
        super(profile, profilesService);
    }

    @Override
    public void execute() throws Exception {
        profilesService.updateProfile(profile);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.UPDATE_USER_BY_ADMIN;
    }
}
