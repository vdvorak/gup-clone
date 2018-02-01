package ua.com.gup.rent.command.rent.profile.bonus;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.command.rent.profile.ProfileCommand;
import ua.com.gup.rent.mapper.ProfileBonusMapper;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;

public abstract class ProfileBonusCommand extends ProfileCommand<ProfileBonus> {
    protected ProfileBonusService profileBonusService;
    protected ProfileBonusMapper profileBonusMapper;
    protected ProfileBonus profileBonus;
    protected String profileBonusId;

    public ProfileBonusCommand(ProfileBonusService rentOfferService, String profileBonusId,  ProfileBonusMapper profileBonusMapper) {
        this.profileBonusService = rentOfferService;
        this.profileBonusId = profileBonusId;
        this.profileBonusMapper = profileBonusMapper;
    }

    protected final ProfileBonus getObjectAfterOperation() {
        return this.profileBonus;
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
                return profileBonusId;
            }

            @Override
            public String getObjectType() {
                return ObjectType.BONUS_SCENARIOS;
            }
        };
    }
}
