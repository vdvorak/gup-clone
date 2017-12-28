package ua.com.gup.server.command.user;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.server.command.SaleCommand;
import ua.com.gup.service.profile.ProfilesService;

public abstract class UserCommand<T extends CommonProfile> extends SaleCommand<T> {

    protected ProfilesService profilesService;
    protected Profile profile;

    public UserCommand(Profile profile, ProfilesService profilesService) {
        this.profilesService = profilesService;
        this.profile = profile;
    }

    protected final Profile getObjectAfterOperation() {
        return this.profile;
    }

    @Override
    public Journalable getJournalable() {
        return new Journalable() {

            @Override
            public Object getObject() {
                return getObjectAfterOperation();
            }

            @Override
            public String getObjectId() {
                return profile.getId();
            }

            @Override
            public String getObjectType() {
                return ObjectType.USER;
            }
        };
    }


}
