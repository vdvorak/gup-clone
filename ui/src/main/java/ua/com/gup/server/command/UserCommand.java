package ua.com.gup.server.command;

import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

public abstract class UserCommand extends SaleCommand {

    protected ProfilesService profilesService;
    protected Profile profile;

    public UserCommand(Profile profile, ProfilesService profilesService) {
        this.profilesService = profilesService;
        this.profile = profile;
    }

    @Override
    public String getObjectType() {
        return ObjectType.USER;
    }
}
