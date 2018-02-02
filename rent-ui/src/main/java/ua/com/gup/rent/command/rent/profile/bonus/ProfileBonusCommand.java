package ua.com.gup.rent.command.rent.profile.bonus;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.command.rent.profile.ProfileCommand;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;

public abstract class ProfileBonusCommand extends ProfileCommand<ProfileBonus> {
    protected ProfileBonusService profileBonusService;
    protected ProfileBonus profileBonus;
    protected String profileBonusId;

    public ProfileBonusCommand(ProfileBonusService profileBonusService, ProfileBonus profileBonus) {
        this.profileBonusService = profileBonusService;

        if (profileBonus != null) {
            this.profileBonus = profileBonus;
            this.profileBonusId = profileBonus.getId();
        }else {
            this.profileBonus = profileBonus;
            this.profileBonusId = null;
        }
    }

    @Override
    public Journalable getJournalable() {
        return new Journalable() {


            @Override
            public Object getObject() {
                return profileBonus;
            }

            @Override
            public String getObjectId() {
                return profileBonusId;
            }

            @Override
            public String getObjectType() {
                return ObjectType.USERS_BONUS;
            }
        };
    }
}
