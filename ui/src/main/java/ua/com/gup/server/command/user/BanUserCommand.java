package ua.com.gup.server.command.user;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

public class BanUserCommand extends UserCommand<Profile> {
    private String privateExp;
    private String publicExp;

    public BanUserCommand(Profile profile, ProfilesService profilesService, String privateExp, String publicExp) {
        super(profile, profilesService);
        this.privateExp = privateExp;
        this.publicExp = publicExp;
    }

    @Override
    public Profile execute() throws Exception {
        profilesService.banProfile(profile, privateExp, publicExp);
        return profile;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.USER_BAN;
    }
}
