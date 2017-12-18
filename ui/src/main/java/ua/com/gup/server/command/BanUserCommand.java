package ua.com.gup.server.command;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

public class BanUserCommand extends UserCommand {
    private String privateExp;
    private String publicExp;

    public BanUserCommand(Profile profile, ProfilesService profilesService, String privateExp, String publicExp) {
        super(profile, profilesService);
        this.privateExp = privateExp;
        this.publicExp = publicExp;
    }

    @Override
    public void execute() throws Exception {
        profilesService.banProfile(profile, privateExp, publicExp);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.USER_BAN;
    }
}
