package ua.com.gup.dto.profile;

import ua.com.gup.mongo.composition.domain.profile.Profile;

public class AdminPrivateProfileDTO extends PrivateProfileDTO {

    private Boolean ban;

    public AdminPrivateProfileDTO(Profile profile) {
        super(profile);
        this.ban = profile.getBan();

    }

    public Boolean getBan() {
        return ban;
    }

    public void setBan(Boolean ban) {
        this.ban = ban;
    }
}
